package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.adapter;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.out.CategoryRepository;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Category;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.CategoryName;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.entity.CategoryEntity;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.mapper.CategoryPersistenceMapper;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.repository.SpringDataCategoryRepository;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Repository
public class R2dbcCategoryRepositoryAdapter implements CategoryRepository {
    private final SpringDataCategoryRepository repository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public R2dbcCategoryRepositoryAdapter(
            SpringDataCategoryRepository repository,
            R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.repository = repository;
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
    }

    @Override
    public Mono<Boolean> existsByName(CategoryName categoryName) {
        return repository.existsByName(categoryName.value());
    }

    @Override
    public Mono<Category> save(Category category) {
        CategoryEntity categoryEntity = CategoryPersistenceMapper.toEntity(category);
        return r2dbcEntityTemplate.upsert(categoryEntity)
                .map(CategoryPersistenceMapper::toDomain);
    }

    @Override
    public Mono<Category> findById(UUID id) {
        return repository.findById(id).map(CategoryPersistenceMapper::toDomain);
    }

    @Override
    public Flux<Category> findAllEnabled() {
         return repository.findByEnabledTrue().map(CategoryPersistenceMapper::toDomain);
    }

    @Override
    public Flux<Category> findAllDisabled() {
        return repository.findByEnabledFalse().map(CategoryPersistenceMapper::toDomain);
    }

    @Override
    public Flux<Category> findAllIncludingDisabled() {
        return null;
    }

}
