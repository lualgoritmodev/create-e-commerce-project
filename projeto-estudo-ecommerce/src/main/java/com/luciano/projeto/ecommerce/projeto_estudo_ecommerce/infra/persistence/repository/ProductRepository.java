package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.repository;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;
public interface ProductRepository
        extends ReactiveCrudRepository<Product, UUID> {
    Flux<Product> findByCategoryId(UUID categoryId);
}
