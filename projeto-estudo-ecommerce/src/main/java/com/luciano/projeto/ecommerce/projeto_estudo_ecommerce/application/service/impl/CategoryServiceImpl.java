package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.service.impl;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.dto.command.CreateCategoryCommand;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.dto.command.RenameCategoryCommand;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.in.CategoryUseCase;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.out.CategoryRepository;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.dto.CategoryResult;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.CategoryNameAlreadyExistsException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Category;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.CategoryName;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.exception.CategoryNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class CategoryServiceImpl implements CategoryUseCase {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<CategoryResult> createCategory(CreateCategoryCommand command) {
        CategoryName categoryName = new CategoryName(command.name());

        return repository.existsByName(categoryName)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new CategoryNameAlreadyExistsException());
                    }
                    Category category = Category.create(categoryName);
                    return repository.save(category);
                }).map(CategoryResult::from);
    }

    @Override
    public Mono<CategoryResult> findById(UUID id) {
        return repository.findById(id).switchIfEmpty(
                Mono.error(new CategoryNotFoundException(id))
        ).map(CategoryResult::from);
    }

    @Override
    public Mono<CategoryResult> renameCategory(UUID id, RenameCategoryCommand command) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new CategoryNotFoundException(id)))
                .flatMap(category -> {
                    CategoryName newName = new CategoryName(command.name());
                        if(category.getName().equals(newName)) {
                            return Mono.just(category);
                        }
                    return repository.existsByNameAndIdNot(newName, id).flatMap(exists ->
                                exists ?
                                        Mono.error(new CategoryNameAlreadyExistsException()
                                        ):renameAndSave(category, newName));


                }).map(CategoryResult::from);

    }

    @Override
    public Mono<Void> enableCategory(UUID id) {
        return repository.findById(id).switchIfEmpty(
                Mono.error(new CategoryNotFoundException(id))
        ).flatMap(category -> { category.enable();
            return repository.save(category);
        }).then();
    }

    @Override
    public Mono<Void> disableCategory(UUID id) {
         return repository.findById(id).switchIfEmpty(
                Mono.error(new CategoryNotFoundException(id))
        ).flatMap(category -> { category.disable();
            return repository.save(category);
         }).then();
    }

    @Override
    public Flux<CategoryResult> findAllCategories() {
        return repository.findAllCategories().map(CategoryResult::from);
    }

    @Override
    public Flux<CategoryResult> findAllDisabled() {
        return repository.findAllDisabled().map(CategoryResult::from);
    }

    @Override
    public Flux<CategoryResult> findAllEnabled() {
        return repository.findAllEnabled().map(CategoryResult::from);
    }

    private Mono<Category> renameAndSave(Category category, CategoryName newName) {
        category.rename(newName);
        return repository.save(category);
    }
}
