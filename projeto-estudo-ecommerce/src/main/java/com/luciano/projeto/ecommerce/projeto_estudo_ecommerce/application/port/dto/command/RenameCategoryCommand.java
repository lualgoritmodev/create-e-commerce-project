package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.dto.command;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Category;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.dto.request.RenameCategory;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record RenameCategoryCommand(
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
