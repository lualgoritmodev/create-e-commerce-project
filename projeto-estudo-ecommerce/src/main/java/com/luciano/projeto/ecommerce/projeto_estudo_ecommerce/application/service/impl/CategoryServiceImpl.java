package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.service.impl;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.out.CategoryRepository;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.service.CategoryService;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.CategoryNameAlreadyExistsException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Category;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.CategoryName;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.request.CategoryRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.request.RenameCategory;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.response.CategoryResponse;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productnotfoundexception.CategoryNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<CategoryResponse> createCategory(CategoryRequest categoryRequest) {
        CategoryName categoryName = new CategoryName(categoryRequest.name());

        return repository.existsByName(categoryName)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new CategoryNameAlreadyExistsException());
                    }
                    Category category = Category.create(categoryName);
                    return repository.save(category);
                }).map(CategoryResponse::from);
    }

    @Override
    public Mono<CategoryResponse> findById(UUID id) {
        return repository.findById(id).switchIfEmpty(
                Mono.error(new CategoryNotFoundException(id))
        ).map(CategoryResponse::from);
    }

    @Override
    public Mono<RenameCategory> renameCategory(UUID id, RenameCategory request) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new CategoryNotFoundException(id)))
                .flatMap(category -> {
                    CategoryName newName = new CategoryName(request.name());
                        if(category.getName().equals(newName)) {
                            return Mono.just(category);
                        }
                    return repository.existsByName(newName).flatMap(exists ->
                                exists ?
                                        Mono.error(new CategoryNameAlreadyExistsException()
                                        ):renameAndSave(category, newName));


                }).map(RenameCategory::from);

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
    public Flux<CategoryResponse> findAllDisabled() {
        return repository.findAllDisabled().map(CategoryResponse::from);
    }

    @Override
    public Flux<CategoryResponse> findAllEnabled() {
        return repository.findAllEnabled().map(CategoryResponse::from);
    }

    private Mono<Category> renameAndSave(Category category, CategoryName newName) {
        category.rename(newName);
        return repository.save(category);
    }
}
