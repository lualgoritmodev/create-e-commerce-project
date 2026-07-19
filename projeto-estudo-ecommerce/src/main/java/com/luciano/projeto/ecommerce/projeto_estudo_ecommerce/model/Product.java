package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("tb_product")
public class Product {

    @Id
    private UUID id;

    @NotBlank
    @Column("name")
    private String name;

    @NotBlank
    @Column("description")
    private String description;

    @NotNull
    @Column("price")
    private BigDecimal price;

    @NotNull
    @Column("active")
    private Boolean active;

    @NotNull
    @Column("created_at")
    private LocalDateTime createdAt;

    @NotNull
    @Column("stock")
    private Integer stock;
    @NotNull
    @Column("category_id")
    private UUID categoryId;

    public Product() {
    }

    public Product(
            UUID id,
            String name,
            String description,
            BigDecimal price,
            Boolean active,
            LocalDateTime createdAt,
            Integer stock,
            UUID categoryId
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
        this.createdAt = createdAt;
        this.stock = stock;
        this.categoryId = categoryId;
    }

    public UUID getId() {
        return id;
    }

    public void defineId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void changePrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void changeActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getStock() {
        return stock;
    }

    public void changeStock(Integer stock) {
        this.stock = stock;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void defineCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    //    public void setCategoryId(UUID categoryId) {
//        this.categoryId = categoryId;
//    }
}
