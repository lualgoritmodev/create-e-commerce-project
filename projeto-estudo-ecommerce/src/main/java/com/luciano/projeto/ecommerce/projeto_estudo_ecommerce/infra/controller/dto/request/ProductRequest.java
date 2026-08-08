package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.request;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.Money;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.ProductName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
        @NotBlank
        @Size(max = 100)
        ProductName name,
        @NotBlank
        @Size(max = 500)
        String description,
        @NotNull
        @DecimalMin("0.01")
        @Digits(integer = 8, fraction = 2)
        Money price,
        @NotNull
        @PositiveOrZero
        Integer stock,
        @NotNull
        UUID categoryId
) {}
