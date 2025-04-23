package com.example.PS40753_TranHoang_SD19308.service;

import com.example.PS40753_TranHoang_SD19308.dto.request.PermissionRequest;
import com.example.PS40753_TranHoang_SD19308.dto.response.PermissionResponse;
import com.example.PS40753_TranHoang_SD19308.entity.Permission;
import com.example.PS40753_TranHoang_SD19308.exception.AppException;
import com.example.PS40753_TranHoang_SD19308.exception.ErrorCode;
import com.example.PS40753_TranHoang_SD19308.mapper.PermissionMapper;
import com.example.PS40753_TranHoang_SD19308.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PermissionResponse getPermission(String permission) {
        return permissionMapper.toPermissionResponse(permissionRepository
                .findById(permission)
                .orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_FOUND)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PermissionResponse> getALL() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
