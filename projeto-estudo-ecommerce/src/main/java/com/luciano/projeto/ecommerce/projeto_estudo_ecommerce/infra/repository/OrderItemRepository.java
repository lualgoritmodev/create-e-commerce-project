package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.repository;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.OrderItem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface OrderItemRepository
        extends ReactiveCrudRepository<OrderItem, UUID> {

    Flux<OrderItem> findByOrderId(UUID orderItemId);
    Flux<OrderItem> findByProductId(UUID productId);
}
