package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.dto.command.CreateCategoryCommand;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.in.CategoryUseCase;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.dto.request.CategoryRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.dto.response.CategoryResponse;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.mapper.CategoryWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryUseCase categoryUseCase;
    private final CategoryWebMapper categoryWebMapper;

    public CategoryController(CategoryUseCase categoryUseCase, CategoryWebMapper categoryWebMapper) {
        this.categoryUseCase = categoryUseCase;
        this.categoryWebMapper = categoryWebMapper;
    }

    @PostMapping
    public Mono<ResponseEntity<CategoryResponse>> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {

        return categoryUseCase.createCategory(
                categoryWebMapper.toCreateCommand(categoryRequest)
        ).map(categoryWebMapper::toResponse)
                .map(response -> ResponseEntity.status(
                        HttpStatus.CREATED
                ).body(response));
    }

}
