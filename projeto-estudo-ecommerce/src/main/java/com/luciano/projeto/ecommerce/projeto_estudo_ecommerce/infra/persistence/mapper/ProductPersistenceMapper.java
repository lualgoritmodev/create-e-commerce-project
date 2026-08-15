package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.mapper;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Product;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.Money;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.ProductName;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.entity.ProductEntity;

public class ProductPersistenceMapper {

    public static ProductEntity toEntity(Product product) {
        return new ProductEntity(
                product.getId(),
                product.getName().value(),
                product.getDescription(),
                product.getPrice().value(),
                product.isActive(),
                product.getCreatedAt(),
                product.getAvailableStock(),
                product.getReservedStock(),
                product.getCategoryId()
        );
    }

    public static Product toDomain(ProductEntity productEntity) {
        return Product.rehydrate(
                productEntity.getId(),
                new ProductName(productEntity.getName()),
                productEntity.getDescription(),
                new Money(productEntity.getPrice()),
                productEntity.isActive(),
                productEntity.getCreatedAt(),
                productEntity.getAvailableStock(),
                productEntity.getReservedStock(),
                productEntity.getCategoryId()
        );

    }

}
