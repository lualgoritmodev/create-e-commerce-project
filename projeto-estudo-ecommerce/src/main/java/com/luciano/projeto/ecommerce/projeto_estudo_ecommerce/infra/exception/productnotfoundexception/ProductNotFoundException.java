package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productnotfoundexception;

import java.util.UUID;

public class ProductNotFoundException extends ResourceNotFoundException {
    public ProductNotFoundException(UUID productId) {
        super(" Product Not Found " + productId);
    }
}
