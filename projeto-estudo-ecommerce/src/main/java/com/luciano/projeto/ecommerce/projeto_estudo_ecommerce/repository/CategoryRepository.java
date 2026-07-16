package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.repository;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;
public interface CategoryRepository
        extends ReactiveCrudRepository<Category, UUID> {
}