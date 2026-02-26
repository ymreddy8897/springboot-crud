package com.comviva.service;

import com.comviva.dto.ProductRequestDto;
import com.comviva.dto.ProductResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto dto);

    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProduct(Long id, ProductRequestDto dto);

    String deleteProduct(Long id);
    Page<ProductResponseDto> getAllProducts(int page, int size);
}