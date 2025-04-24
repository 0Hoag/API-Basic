package com.example.PS40753_TranHoang_SD19308.service;

import com.example.PS40753_TranHoang_SD19308.constant.PredefinedRole;
import com.example.PS40753_TranHoang_SD19308.dto.request.ProductRequest;
import com.example.PS40753_TranHoang_SD19308.dto.request.ProductUpdateRequest;
import com.example.PS40753_TranHoang_SD19308.dto.request.UserRequest;
import com.example.PS40753_TranHoang_SD19308.dto.response.ProductResponse;
import com.example.PS40753_TranHoang_SD19308.dto.response.UserResponse;
import com.example.PS40753_TranHoang_SD19308.entity.Role;
import com.example.PS40753_TranHoang_SD19308.exception.AppException;
import com.example.PS40753_TranHoang_SD19308.exception.ErrorCode;
import com.example.PS40753_TranHoang_SD19308.mapper.ProductMapper;
import com.example.PS40753_TranHoang_SD19308.mapper.UserMapper;
import com.example.PS40753_TranHoang_SD19308.repository.ProductRepository;
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
public class ProductService {
	ProductRepository productRepository;
    ProductMapper productMapper;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductResponse Create(ProductRequest request) {
        try {
			if (request.getCategory().size() < 0) {
            	throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
            }

            var product = productMapper.toProduct(request);

            productRepository.save(product);

            return productMapper.toProductResponse(product);
        }catch (Exception e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
    }

    public List<ProductResponse> List() {
        return productRepository.findAll()
                .stream().map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public ProductResponse Detail(Integer id) {
        var user = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return productMapper.toProductResponse(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductResponse Update(Integer id, ProductUpdateRequest request) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        productMapper.update(product, request);

		return productMapper.toProductResponse(productRepository.save(product));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void Delete(Integer id) {
        productRepository.deleteById(id);
    }
}
