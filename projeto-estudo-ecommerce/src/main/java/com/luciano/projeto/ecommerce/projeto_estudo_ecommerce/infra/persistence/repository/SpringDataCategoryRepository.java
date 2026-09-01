package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.repository;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.entity.CategoryEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SpringDataCategoryRepository extends
        ReactiveCrudRepository<CategoryEntity, UUID> {
    Mono<Boolean> existsByNameIgnoreCase(String name);
    Mono<Boolean> existsByNameAndIdNot(String name, UUID id);
    Flux<CategoryEntity> findByEnabledTrue();
    Flux<CategoryEntity> findByEnabledFalse();

}
