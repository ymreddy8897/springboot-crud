package com.comviva.service;

import com.comviva.dto.ProductRequestDto;
import com.comviva.dto.ProductResponseDto;
import com.comviva.entity.Product;
import com.comviva.exception.ResourceNotFoundException;
import com.comviva.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto dto) {

        Product product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();

        Product savedProduct = repository.save(product);

        return mapToResponse(savedProduct);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));

        return mapToResponse(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        Product updatedProduct = repository.save(product);

        return mapToResponse(updatedProduct);
    }

    @Override
    public String deleteProduct(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));

        repository.delete(product);

        return "Product deleted successfully with id: " + id;
    }

    private ProductResponseDto mapToResponse(Product product) {

        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}