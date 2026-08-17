package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.out.ProductRepository;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Product;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.mapper.ProductPersistenceMapper;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.repository.SpringDataProductRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Repository
public class R2dbcProductRepositoryAdapter implements ProductRepository {

    private final SpringDataProductRepository repository;

    public R2dbcProductRepositoryAdapter(SpringDataProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Product> save(Product product) {
        return null;
    }

    @Override
    public Mono<Product> findById(UUID productId) {
        return repository.findById(productId).map(
                ProductPersistenceMapper::toDomain
        );
    }

    @Override
    public Flux<Product> findByCategoryId(UUID categoryid) {
        return repository.findByCategoryId(categoryid)
                .map(ProductPersistenceMapper::toDomain);
    }

}
