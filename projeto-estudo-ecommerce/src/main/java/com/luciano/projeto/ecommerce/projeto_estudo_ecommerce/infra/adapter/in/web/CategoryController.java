package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.dto.CategoryResult;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.dto.command.CreateCategoryCommand;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.dto.command.RenameCategoryCommand;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.in.CategoryUseCase;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.dto.request.CategoryRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.dto.response.CategoryResponse;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.mapper.CategoryWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("v1/categories")
public class CategoryController {

    private final CategoryUseCase categoryUseCase;
    private final CategoryWebMapper categoryWebMapper;

    public CategoryController(CategoryUseCase categoryUseCase, CategoryWebMapper categoryWebMapper) {
        this.categoryUseCase = categoryUseCase;
        this.categoryWebMapper = categoryWebMapper;
    }

    @PostMapping
    public Mono<ResponseEntity<CategoryResponse>> createCategory(
            @Valid @RequestBody CategoryRequest categoryRequest) {

        return categoryUseCase.createCategory(
                categoryWebMapper.toCreateCommand(categoryRequest)
        ).map(categoryWebMapper::toResponse)
                .map(response -> ResponseEntity.status(
                        HttpStatus.CREATED
                ).body(response));
    }

    @GetMapping("/category/{id}")
    public Mono<ResponseEntity<CategoryResponse>> findById(
            @Valid UUID id) {
        return categoryUseCase.findById(id)
                .map(categoryWebMapper::toResponse)
                .map(response -> ResponseEntity.status(
                        HttpStatus.OK
                ).body(response));
    }

    @GetMapping("/all-disabled")
    public Flux<ResponseEntity<CategoryResponse>> findAllDisabled() {
        return categoryUseCase.findAllDisabled()
                .map(categoryWebMapper::toResponse)
                .map(response -> ResponseEntity.status(
                        HttpStatus.OK
                ).body(response));
    }

    @GetMapping("/all-enabled")
    public Flux<ResponseEntity<CategoryResponse>> findAllEnabled() {
        return categoryUseCase.findAllEnabled()
                .map(categoryWebMapper::toResponse)
                .map(response -> ResponseEntity.status(
                        HttpStatus.OK
                ).body(response));

    }

    @PutMapping("/rename-category/{id}")
    public Mono<ResponseEntity<CategoryResponse>> renameCategory(
            @Valid UUID id, RenameCategoryCommand renameCategoryCommand) {

        return categoryUseCase.renameCategory(id, renameCategoryCommand)
                .map(categoryWebMapper::toResponse)
                .map(response -> ResponseEntity.status(
                        HttpStatus.NO_CONTENT
                ).body(response));

    }

    @PutMapping("/enable-category{id}")
    public Mono<ResponseEntity<Void>> enableCategory(@Valid UUID id) {

        return categoryUseCase.enableCategory(id)
                .map(response -> ResponseEntity.status(
                        HttpStatus.NO_CONTENT
                ).body(response));

    }

    @PutMapping("/disable-category{id}")
    public Mono<ResponseEntity<Void>> disableCategory(@Valid UUID id) {

        return categoryUseCase.disableCategory(id)
                .map(response -> ResponseEntity.status(
                        HttpStatus.NO_CONTENT
                ).body(response));

    }

    @GetMapping("/all-categories")
    public Flux<ResponseEntity<CategoryResponse>> findAllCategories() {

        return categoryUseCase.findAllCategories()
                .map(categoryWebMapper::toResponse)
                .map(response -> ResponseEntity.status(
                        HttpStatus.OK
                ).body(response));
    }

}
