package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.repository;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Address;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface AddressRepository
        extends ReactiveCrudRepository<Address, UUID> {
    Flux<Address> findByCustomerId(UUID customerId);
}