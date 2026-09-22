package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.result;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Category;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.response.CategoryResponse;

import java.util.UUID;

public record CategoryResult(
        UUID id,
        String name,
        boolean isEnabled
) {

    public static CategoryResult from(Category category) {
        return new CategoryResult(
                category.getId(),
                category.getName().value(),
                category.isEnabled()
        );
    }
}
