package com.example.PS40753_TranHoang_SD19308.service;

import com.example.PS40753_TranHoang_SD19308.dto.request.RoleRequest;
import com.example.PS40753_TranHoang_SD19308.dto.response.RoleResponse;
import com.example.PS40753_TranHoang_SD19308.exception.AppException;
import com.example.PS40753_TranHoang_SD19308.exception.ErrorCode;
import com.example.PS40753_TranHoang_SD19308.mapper.RoleMapper;
import com.example.PS40753_TranHoang_SD19308.repository.PermissionRepository;
import com.example.PS40753_TranHoang_SD19308.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoleResponse getRole(String role) {
        return roleMapper.toRoleResponse(
                roleRepository.findById(role).orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_FOUND)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
