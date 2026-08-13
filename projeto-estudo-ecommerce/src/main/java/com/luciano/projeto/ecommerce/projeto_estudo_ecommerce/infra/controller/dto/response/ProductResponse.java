package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.response;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Product;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        boolean active,
        int availableStock,

        int reservedStock,
        LocalDateTime createdAt,
        UUID categoryId
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName().value(),
                product.getDescription(),
                product.getPrice().value(),
                product.isActive(),
                product.getAvailableStock(),
                product.getReservedStock(),
                product.getCreatedAt(),
                product.getCategoryId()

        );
    }
    
}
