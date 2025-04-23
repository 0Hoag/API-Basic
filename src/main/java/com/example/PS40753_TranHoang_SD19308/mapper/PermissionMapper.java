package com.example.PS40753_TranHoang_SD19308.mapper;

import com.example.PS40753_TranHoang_SD19308.dto.request.PermissionRequest;
import com.example.PS40753_TranHoang_SD19308.dto.response.PermissionResponse;
import com.example.PS40753_TranHoang_SD19308.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
