package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.service;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.request.ProductRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.request.ProductUpdateRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductService {
    Mono<ProductResponse> createProduct(ProductRequest productRequest);
    Mono<ProductResponse> getProductById(UUID uuid);
    Flux<ProductResponse> getAllProducts();

    Mono<ProductResponse> updateProduct(UUID productId, ProductUpdateRequest productUpdateRequest);
}
