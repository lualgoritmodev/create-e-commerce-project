package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.service;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.request.ProductRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.response.ProductResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductService {
    Mono<ProductResponse> createProduct(ProductRequest productRequest);
    Mono<ProductResponse> getProductById(UUID uuid);
    Flux<ProductResponse> getAllProducts();
}
