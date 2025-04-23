package com.example.PS40753_TranHoang_SD19308.mapper;

import com.example.PS40753_TranHoang_SD19308.dto.request.RoleRequest;
import com.example.PS40753_TranHoang_SD19308.dto.response.RoleResponse;
import com.example.PS40753_TranHoang_SD19308.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
