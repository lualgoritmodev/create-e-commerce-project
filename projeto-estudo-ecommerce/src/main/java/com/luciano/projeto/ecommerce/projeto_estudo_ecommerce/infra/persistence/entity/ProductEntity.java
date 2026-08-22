package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("tb_product")
public class ProductEntity {

    @Id
    private UUID id;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("price")
    private BigDecimal price;

    @Column("active")
    private boolean active;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("available_stock")
    private int availableStock;

    @Column("reserved_stock")
    private int reservedStock;

    @Column("category_id")
    private UUID categoryId;

    @PersistenceCreator
    public ProductEntity(
            UUID id,
            String name,
            String description,
            BigDecimal price,
            boolean active,
            LocalDateTime createdAt,
            int availableStock,
            int reservedStock,
            UUID categoryId
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
        this.createdAt = createdAt;
        this.availableStock = availableStock;
        this.reservedStock = reservedStock;
        this.categoryId = categoryId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public int getReservedStock() {
        return reservedStock;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

}
