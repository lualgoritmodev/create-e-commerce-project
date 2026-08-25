package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.mapper;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Category;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.CategoryName;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.entity.CategoryEntity;

public class CategoryPersistenceMapper {

    public static CategoryEntity toEntity(Category category) {
         return new CategoryEntity(
                 category.getId(),
                 category.getName().value(),
                 category.isEnabled()
         );

    }

    public static Category toDomain(CategoryEntity categoryEntity) {
         return Category.rehydrate(
                 categoryEntity.getId(),
                 new CategoryName(categoryEntity.getName()),
                 categoryEntity.isEnabled()
         );

    }
}
