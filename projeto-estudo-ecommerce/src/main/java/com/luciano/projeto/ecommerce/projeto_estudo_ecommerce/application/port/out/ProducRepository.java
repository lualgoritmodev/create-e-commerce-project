package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.out;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProducRepository {

    Mono<Product> save(Product product);

    Mono<Product> findByid(UUID productId);

    Flux<Product> findByCategoryId(UUID categoryid);

    Flux<Product> getAllProduct();
}
