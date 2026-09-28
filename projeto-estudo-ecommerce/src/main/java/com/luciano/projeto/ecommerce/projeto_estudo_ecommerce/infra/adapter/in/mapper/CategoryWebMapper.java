package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.mapper;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.dto.CategoryResult;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.dto.command.CreateCategoryCommand;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.dto.command.RenameCategoryCommand;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.dto.request.CategoryRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.dto.request.RenameCategory;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.dto.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryWebMapper {

    public CreateCategoryCommand toCreateCommand(CategoryRequest request) {
        return new CreateCategoryCommand(request.name());
    }

    public CategoryResponse toResponse(CategoryResult result) {
        return new CategoryResponse(result.id(), result.name(), result.isEnabled());
    }

    public RenameCategoryCommand toRenameCategory(CategoryRequest request) {
        return new RenameCategoryCommand(null, request.name());
    }
}
