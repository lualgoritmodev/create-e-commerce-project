package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.exception;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.Product;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(UUID productId) {
        super(" Product Not Found " + productId);
    }
}
