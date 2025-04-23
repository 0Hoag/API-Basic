package com.example.PS40753_TranHoang_SD19308.mapper;

import com.example.PS40753_TranHoang_SD19308.dto.request.UpdatePasswordRequest;
import com.example.PS40753_TranHoang_SD19308.dto.request.UserRequest;
import com.example.PS40753_TranHoang_SD19308.dto.response.UserResponse;
import com.example.PS40753_TranHoang_SD19308.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UpdatePasswordRequest request);
}
