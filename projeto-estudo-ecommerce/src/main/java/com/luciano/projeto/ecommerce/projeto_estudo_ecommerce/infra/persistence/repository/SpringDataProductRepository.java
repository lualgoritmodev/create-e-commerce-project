package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.repository;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.entity.ProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;
public interface SpringDataProductRepository
        extends ReactiveCrudRepository<ProductEntity, UUID> {
    Flux<ProductEntity> findByCategoryId(UUID categoryId);
}
