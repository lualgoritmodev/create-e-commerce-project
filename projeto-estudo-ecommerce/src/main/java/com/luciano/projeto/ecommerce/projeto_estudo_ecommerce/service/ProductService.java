package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.service;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.Product;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> createProduct(Product product);
}
