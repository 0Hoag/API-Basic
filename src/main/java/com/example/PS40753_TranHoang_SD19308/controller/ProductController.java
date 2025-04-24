package com.example.PS40753_TranHoang_SD19308.controller;

import com.example.PS40753_TranHoang_SD19308.dto.ApiResponse;
import com.example.PS40753_TranHoang_SD19308.dto.request.ProductRequest;
import com.example.PS40753_TranHoang_SD19308.dto.request.ProductUpdateRequest;
import com.example.PS40753_TranHoang_SD19308.dto.request.UserRequest;
import com.example.PS40753_TranHoang_SD19308.dto.response.ProductResponse;
import com.example.PS40753_TranHoang_SD19308.dto.response.UserResponse;
import com.example.PS40753_TranHoang_SD19308.service.ProductService;
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
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @PostMapping("/")
    ApiResponse<ProductResponse> create(@RequestBody @Valid ProductRequest request) throws SQLException, IOException {
        return ApiResponse.<ProductResponse>builder()
                .code(1000)
                .result(productService.Create(request))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<ProductResponse> detail(@PathVariable("id") Integer id) {
        return ApiResponse.<ProductResponse>builder()
                .code(1000)
                .result(productService.Detail(id))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<ProductResponse> update(@PathVariable("id") Integer id, @RequestBody ProductUpdateRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .code(1000)
                .result(productService.Update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> update(@PathVariable("id") Integer id) {
        productService.Delete(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Delete product success")
                .build();
    }


    @GetMapping("/all")
    ApiResponse<List<ProductResponse>> List() {
        return ApiResponse.<List<ProductResponse>>builder()
                .code(1000)
                .result(productService.List())
                .build();
    }
}
