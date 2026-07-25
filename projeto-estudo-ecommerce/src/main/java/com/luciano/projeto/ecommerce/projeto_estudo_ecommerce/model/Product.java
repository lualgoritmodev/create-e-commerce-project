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

    @Column("active")
    private boolean isActive;

    @NotNull
    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("stock")
    private int stock;

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
            boolean active,
            LocalDateTime createdAt,
            int stock,
            UUID categoryId
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isActive = active;
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

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do produto não pode estar vazio."
            );
        }

        this.name = newName;
    }

    public String getDescription() {
        return description;
    }

    public void changeDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException(
                    "A descrição do produto não pode estar vazia."
            );
        }

        this.description = description;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void changePrice(BigDecimal newPrice) {
        if (newPrice == null || newPrice.signum() <= 0) {
            throw new IllegalArgumentException(
                    "O preço deve ser informado e maior que zero."
            );
        }

        this.price = newPrice;
    }

    public boolean isActive() {
        return isActive;
    }

    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void defineCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getStock() {
        return stock;
    }

    public void updateStock(int name) {
        if (name < 0) {
            throw new IllegalArgumentException(
                    "O estoque não pode ser negativo."
            );
        }

        this.stock = name;
    }

    public void defineCategory(UUID categoryId) {
        if(categoryId == null) {
            throw new IllegalArgumentException("CategoryId is required");
        }

         this.categoryId = categoryId;
    }
    public UUID getCategoryId() {
        return categoryId;
    }
    public boolean isOutOfStock() {
        return this.stock <= 0;
    }

    public boolean hasEnoughStock(int quantity) {
        return stock >= quantity;
    }
    public boolean isInactive() {
        return !this.isActive();
    }
    public boolean canSell(int quantity) {
        return isActive() &&
                !isOutOfStock() && hasEnoughStock(quantity);
    }
    public static Product create(
            String name,
            String description,
            BigDecimal price,
            Integer stock,
            UUID categoryId

    ) {
        Product product =  new Product();
        product.defineId(UUID.randomUUID());
        product.rename(name);
        product.changeDescription(description);
        product.changePrice(price);
        product.updateStock(stock);
        product.activate();
        product.defineCreatedAt(LocalDateTime.now());
        product.defineCategory(categoryId);

        return product;
    }

}
