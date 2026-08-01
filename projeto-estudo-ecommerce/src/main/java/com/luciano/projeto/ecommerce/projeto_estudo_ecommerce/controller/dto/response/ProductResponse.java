package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.response;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.dominio.model.Product;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.dominio.valueobject.ProductName;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        ProductName name,
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
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.isActive(),
                product.getAvailableStock(),
                product.getReservedStock(),
                product.getCreatedAt(),
                product.getCategoryId()

        );
    }
    
}
