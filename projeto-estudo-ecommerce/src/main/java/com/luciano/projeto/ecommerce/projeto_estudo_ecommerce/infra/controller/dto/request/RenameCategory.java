package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.request;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Category;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record RenameCategory(
        UUID id,
        @NotBlank String name
) {
    public static RenameCategory from(Category category) {
        return new RenameCategory(
                category.getId(),
                category.getName().value()
        );
    }
}
