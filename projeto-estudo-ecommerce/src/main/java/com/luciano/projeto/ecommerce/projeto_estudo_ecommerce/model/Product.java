package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 500)
    private String description;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @NotNull
    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @NotNull
    @Column(nullable = false)
    private Integer stock;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderItem> items = new ArrayList<>();

    public Product() {
    }

    public Product(UUID id, String name, String description,
                   BigDecimal price, Boolean active,
                   LocalDateTime createdAt, Integer stock, Category category, List<OrderItem> items) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
        this.createdAt = createdAt;
        this.stock = stock;
        this.category = category;
        this.items = items;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void addItem(OrderItem item) {
        items.add(item);
        item.setProduct(this);
    }
}
