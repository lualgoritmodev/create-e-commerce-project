package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.in;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.command.CreateCategoryCommand;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.command.RenameCategoryCommand;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.result.CategoryResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CategoryUseCase {

    Mono<CategoryResult> createCategory(CreateCategoryCommand command);
    Mono<CategoryResult> findById(UUID id);
    Flux<CategoryResult> findAllDisabled();
    Flux<CategoryResult> findAllEnabled();
    Mono<CategoryResult> renameCategory(UUID id, RenameCategoryCommand command);
    Mono<Void> enableCategory(UUID id);
    Mono<Void> disableCategory(UUID id);
    Flux<CategoryResult> findAllCategories();
}
