package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.entity;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Table("tb_category")
public class CategoryEntity {

    @Id
    private UUID id;

    @Column("name")
    private String name;

    @Column("active")
    private boolean active;

    @PersistenceCreator
    public CategoryEntity(
            UUID id,
            String name,
            boolean active
    ) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }
}