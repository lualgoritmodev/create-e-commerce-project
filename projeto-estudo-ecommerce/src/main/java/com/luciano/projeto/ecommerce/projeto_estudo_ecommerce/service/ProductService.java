package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.service;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.request.ProductRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.response.ProductResponse;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.Product;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ProductResponse> createProduct(ProductRequest productRequest);
}
