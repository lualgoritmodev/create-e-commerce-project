package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.out;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductRepository {

    Mono<Product> save(Product product);

    Mono<Product> findById(UUID productId);

    Flux<Product> findByCategoryId(UUID categoryId);

    Flux<Product> findAll();

}
