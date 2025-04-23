package com.example.PS40753_TranHoang_SD19308.service;

import com.example.PS40753_TranHoang_SD19308.constant.PredefinedRole;
import com.example.PS40753_TranHoang_SD19308.dto.request.UserRequest;
import com.example.PS40753_TranHoang_SD19308.dto.response.UserResponse;
import com.example.PS40753_TranHoang_SD19308.entity.Role;
import com.example.PS40753_TranHoang_SD19308.exception.AppException;
import com.example.PS40753_TranHoang_SD19308.exception.ErrorCode;
import com.example.PS40753_TranHoang_SD19308.mapper.UserMapper;
import com.example.PS40753_TranHoang_SD19308.repository.RoleRepository;
import com.example.PS40753_TranHoang_SD19308.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
	UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public UserResponse Create(UserRequest request) {
        try {
            var username = userRepository.existsByUsername(request.getUsername());
            var email = userRepository.existsByEmail(request.getEmail());

            if (username || email) {
                throw new AppException(ErrorCode.USERNAME_INVALID);
            }

            var user = userMapper.toUser(request);
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            HashSet<Role> roles = new HashSet<>();
            roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);
            user.setRoles(roles);

            userRepository.save(user);

            return userMapper.toUserResponse(user);
        }catch (Exception e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserResponse> List() {
        return userRepository.findAll()
                .stream().map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserResponse Detail(String id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }
}
