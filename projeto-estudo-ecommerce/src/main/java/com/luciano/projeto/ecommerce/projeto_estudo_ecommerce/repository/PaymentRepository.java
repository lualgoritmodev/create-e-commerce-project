package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.repository;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface PaymentRepository extends
        ReactiveCrudRepository<Payment, UUID> {
}
