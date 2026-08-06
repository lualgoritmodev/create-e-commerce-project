package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.repository;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;
public interface CustomerRepository
        extends ReactiveCrudRepository<Customer, UUID> {
}
