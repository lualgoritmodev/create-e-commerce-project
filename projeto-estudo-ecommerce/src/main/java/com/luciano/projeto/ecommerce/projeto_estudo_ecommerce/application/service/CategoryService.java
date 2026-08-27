package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.service;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.request.CategoryRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.request.RenameCategory;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.response.CategoryResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CategoryService {

    Mono<CategoryResponse> createCategory(CategoryRequest categoryRequest);
    Mono<CategoryResponse> findById(UUID id);
    Flux<CategoryResponse> findAllDisabled();
    Flux<CategoryResponse> findAllEnabled();
    Mono<RenameCategory> renameCategory(UUID id, RenameCategory request);
    Mono<Void> enableCategory(UUID id);
    Mono<Void> disableCategory(UUID id);
}
