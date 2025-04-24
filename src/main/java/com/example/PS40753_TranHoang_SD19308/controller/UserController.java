package com.example.PS40753_TranHoang_SD19308.controller;

import com.example.PS40753_TranHoang_SD19308.dto.ApiResponse;
import com.example.PS40753_TranHoang_SD19308.dto.request.UserRequest;
import com.example.PS40753_TranHoang_SD19308.dto.response.UserResponse;
import com.example.PS40753_TranHoang_SD19308.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping("/signup")
    ApiResponse<UserResponse> create(@RequestBody @Valid UserRequest request) throws SQLException, IOException {
        log.info("Controller: create user");
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.Create(request))
                .build();
    }

    @GetMapping("/all")
    ApiResponse<List<UserResponse>> List() {
        return ApiResponse.<List<UserResponse>>builder()
                .code(1000)
                .result(userService.List())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<UserResponse> detail(@PathVariable("id") Integer id) {
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.Detail(id))
                .build();
    }
}
