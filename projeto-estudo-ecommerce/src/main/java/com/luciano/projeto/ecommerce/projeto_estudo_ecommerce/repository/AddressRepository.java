package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.repository;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.Address;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface AddressRepository extends
        ReactiveCrudRepository<Address, UUID> {
}
