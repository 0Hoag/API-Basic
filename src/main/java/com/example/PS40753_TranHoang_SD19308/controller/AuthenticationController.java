package com.example.PS40753_TranHoang_SD19308.controller;

import com.example.PS40753_TranHoang_SD19308.dto.ApiResponse;
import com.example.PS40753_TranHoang_SD19308.dto.request.*;
import com.example.PS40753_TranHoang_SD19308.dto.response.AuthenticationResponse;
import com.example.PS40753_TranHoang_SD19308.dto.response.IntrospectResponse;
import com.example.PS40753_TranHoang_SD19308.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> getToken(@RequestBody AuthenticationRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .result(authenticationService.authenticated(request))
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder()
                .code(1000)
                .result(authenticationService.introspectResponse(request))
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().code(1000).build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .result(authenticationService.refreshToken(request))
                .build();
    }
}
