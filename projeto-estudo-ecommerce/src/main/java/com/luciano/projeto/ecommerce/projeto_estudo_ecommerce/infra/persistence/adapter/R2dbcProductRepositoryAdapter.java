package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.adapter;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.out.ProductRepository;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Product;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.entity.ProductEntity;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.mapper.ProductPersistenceMapper;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.repository.SpringDataProductRepository;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Repository
public class R2dbcProductRepositoryAdapter implements ProductRepository {

    private final SpringDataProductRepository repository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public R2dbcProductRepositoryAdapter
            (SpringDataProductRepository repository,
             R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.repository = repository;
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
    }

    @Override
    public Mono<Product> save(Product product) {

        ProductEntity productEntity = ProductPersistenceMapper.toEntity(product);
        return r2dbcEntityTemplate.upsert(productEntity)
                .map(ProductPersistenceMapper::toDomain);
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
