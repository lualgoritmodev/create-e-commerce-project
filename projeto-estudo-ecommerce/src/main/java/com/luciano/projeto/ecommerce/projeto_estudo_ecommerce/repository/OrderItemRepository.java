package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.repository;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.OrderItem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface OrderItemRepository extends
        ReactiveCrudRepository<OrderItem, UUID> {
}
