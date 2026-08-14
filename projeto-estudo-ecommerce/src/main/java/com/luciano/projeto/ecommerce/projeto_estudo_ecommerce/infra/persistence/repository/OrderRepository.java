package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.repository;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;
public interface OrderRepository
        extends ReactiveCrudRepository<Order, UUID> {
    Flux<Order> findByCustomerId(UUID customerId);
}
