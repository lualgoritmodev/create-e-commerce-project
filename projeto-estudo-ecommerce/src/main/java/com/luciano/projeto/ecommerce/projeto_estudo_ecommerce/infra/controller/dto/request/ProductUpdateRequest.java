package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.request;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Product;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.Money;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.ProductName;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ProductUpdateRequest(

        @NotBlank(message = "Product name is required")
        @Size(
                max = 100,
                message = "Product name must have at most 100 characters"
        )
        ProductName name,

        @NotBlank(message = "Product description is required")
        @Size(
                max = 500,
                message = "Product description must have at most 500 characters"
        )
        String description,

        @NotNull(message = "Product price is required")
        @DecimalMin(
                value = "0.01",
                message = "Product price must be greater than zero"
        )
        @Digits(
                integer = 8,
                fraction = 2,
                message = "Product price must have up to 8 integer digits and 2 decimal places"
        )
        Money price

) {

    public Product updateEntity(Product product) {
        product.rename(name);
        product.changeDescription(description);
        product.changePrice(price);

        return product;
    }
}