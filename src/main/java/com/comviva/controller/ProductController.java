package com.comviva.controller;

import com.comviva.dto.ProductRequestDto;
import com.comviva.dto.ProductResponseDto;
import com.comviva.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService service;

    @PostMapping
    public ProductResponseDto create(@Valid @RequestBody ProductRequestDto dto) {
        return service.createProduct(dto);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @GetMapping
    public List<ProductResponseDto> getAll() {
        return service.getAllProducts();
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @Valid @RequestBody ProductRequestDto dto) {
        return service.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        return ResponseEntity.ok(service.deleteProduct(id));
    }

}
