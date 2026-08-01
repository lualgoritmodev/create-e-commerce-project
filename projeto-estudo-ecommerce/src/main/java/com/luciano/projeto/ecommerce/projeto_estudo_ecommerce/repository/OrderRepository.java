package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.repository;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.dominio.model.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;
public interface OrderRepository
        extends ReactiveCrudRepository<Order, UUID> {
    Flux<Order> findByCustomerId(UUID customerId);
}
