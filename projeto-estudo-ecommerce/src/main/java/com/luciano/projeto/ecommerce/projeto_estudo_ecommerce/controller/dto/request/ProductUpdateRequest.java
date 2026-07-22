package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.request;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.Product;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductUpdateRequest(

        @NotBlank(message = "Product name is required")
        String name,

        @NotBlank(message = "Product description is required")
        String description,

        @NotNull(message = "Product price is required")
        @DecimalMin(
                value = "0.01",
                message = "Product price must be greater than zero"
        )
        BigDecimal price,

        @NotNull
        boolean active,
        @NotNull(message = "Product stock is required")
        @PositiveOrZero(message = "Product stock cannot be negative")
        Integer stock,

        UUID categoryId

) {
    public Product toEntity() {
        return new Product(
                null,
                this.name,
                this.description,
                this.price,
                null,
                null,
                this.stock,
                this.categoryId
        );
    }
}
