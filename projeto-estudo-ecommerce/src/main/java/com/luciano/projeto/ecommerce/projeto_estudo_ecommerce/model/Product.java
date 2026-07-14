package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table("tb_product")
public class Product {

    @Id
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Boolean active;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private Category category;

    private List<OrderItem> items = new ArrayList<>();

    @NotNull
    private Integer stock;

    public Product() {
    }

    public Product(
            UUID id,
            String name,
            String description,
            BigDecimal price,
            Boolean active,
            LocalDateTime createdAt,
            Integer stock
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
        this.createdAt = createdAt;
        this.stock = stock;
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

    public void setCategory(Category category) {
        this.category = category;
    }
    public Integer getStock() {
        return stock;
    }

    public void changeStock(Integer stock) {
        this.stock = stock;
    }
}
