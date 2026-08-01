package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.repository;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.dominio.model.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PaymentRepository
        extends ReactiveCrudRepository<Payment, UUID> {
    Mono<Payment> findByOrderId(UUID orderId);
}
