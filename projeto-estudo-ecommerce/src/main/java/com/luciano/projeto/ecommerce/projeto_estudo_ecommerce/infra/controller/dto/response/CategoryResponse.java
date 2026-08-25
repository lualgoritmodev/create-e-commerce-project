package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.response;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Category;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name,
        boolean active
) {

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName().value(),
                category.isEnabled()
        );
    }
}
