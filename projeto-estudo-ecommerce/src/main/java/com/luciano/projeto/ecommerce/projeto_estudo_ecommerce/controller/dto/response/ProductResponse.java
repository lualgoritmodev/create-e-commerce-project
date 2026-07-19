package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.response;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Boolean active,
        Integer stock,
        LocalDateTime createdAt,
        UUID categoryId
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getActive(),
                product.getStock(),
                product.getCreatedAt(),
                product.getCategoryId()

        );
    }
    
}
