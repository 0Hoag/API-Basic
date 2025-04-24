package com.example.PS40753_TranHoang_SD19308.mapper;

import com.example.PS40753_TranHoang_SD19308.dto.request.ProductRequest;
import com.example.PS40753_TranHoang_SD19308.dto.request.ProductUpdateRequest;
import com.example.PS40753_TranHoang_SD19308.dto.response.ProductResponse;
import com.example.PS40753_TranHoang_SD19308.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequest request);

    ProductResponse toProductResponse(Product entity);

    void update(@MappingTarget Product entity, ProductUpdateRequest request);
}
