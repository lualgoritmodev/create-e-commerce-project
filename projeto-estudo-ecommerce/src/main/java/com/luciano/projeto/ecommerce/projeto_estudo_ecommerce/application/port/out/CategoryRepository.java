package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.out;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Category;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.CategoryName;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface CategoryRepository {

    Mono<Boolean> existsByName(CategoryName categoryName);

    Mono<Category> save(Category category);

    Mono<Category> findById(UUID id);

    Flux<Category> findAllEnabled();

    Flux<Category> findAllDisabled();

    Flux<Category> findAllCategories();

    Mono<Boolean>existsByNameAndIdNot(CategoryName categoryName, UUID id);

}
