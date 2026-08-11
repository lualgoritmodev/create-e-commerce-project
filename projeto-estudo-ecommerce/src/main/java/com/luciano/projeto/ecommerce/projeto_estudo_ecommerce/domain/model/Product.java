package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.*;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.Money;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.ProductName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("tb_product")
public class Product {

    @Id
    private UUID id;

    @NotNull
    @Column("name")
    private ProductName name;

    @NotBlank
    @Column("description")
    private String description;

    @NotNull
    @Column("price")
    private Money price;

    @Column("active")
    private boolean isActive;

    @NotNull
    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("available_stock")
    private int availableStock;
    @Column("reserved_stock")
    private int reservedStock;
    @NotNull
    @Column("category_id")
    private UUID categoryId;

    private Product() {
    }

    @PersistenceCreator
    public Product(
            UUID id,
            ProductName name,
            String description,
            Money price,
            boolean active,
            LocalDateTime createdAt,
            int availableStock,
            int reservedStock,
            UUID categoryId
    ) {
        this.id = id;
        rename(name);
        changeDescription(description);
        changePrice(price);
        this.isActive = active;
        this.createdAt = createdAt;
        restoreStock(availableStock, reservedStock);
        defineCategory(categoryId);
    }

    public UUID getId() {
        return id;
    }

    public void defineId(UUID id) {
        this.id = id;
    }

    public ProductName getName() {
        return name;
    }

    public void rename(ProductName newName) {
        if (newName == null) {
            throw new InvalidProductNameException();
        }

        this.name = newName;
    }

    public String getDescription() {
        return description;
    }

    public void changeDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new InvalidProductDescriptionException();
        }

        this.description = description;
    }
    public Money getPrice() {
        return price;
    }

    public void changePrice(Money price) {
        if (price == null || !price.isPositive()) {
            throw new InvalidProductPriceException();
        }

        this.price = price;
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

    public int getAvailableStock() {
        return availableStock;
    }

    public int getReservedStock() {
        return reservedStock;
    }

    public void defineInitialStock(int quantity) {

        validateNonNegativeQuantity(quantity);
        this.availableStock = quantity;
        this.reservedStock = 0;
    }

    private void restoreStock(int availableStock, int reservedStock) {

        validateNonNegativeQuantity(availableStock);
        validateNonNegativeQuantity(reservedStock);

        this.availableStock = availableStock;
        this.reservedStock = reservedStock;

    }

    private void validateNonNegativeQuantity(int quantity) {
        if(quantity < 0) {
            throw new InvalidNegativeQuantityException();
        }

    }

    private void validatePositiveQuantity(int quantity) {
        if(quantity <= 0) {
            throw new InvalidQuantityException();
        }
    }

    public void reserveStock(int quantity) {

        validatePositiveQuantity(quantity);
        if(!hasEnoughStock(quantity)) {
            throw new InsufficientStockException();
        }

        this.availableStock = availableStock - quantity;
        this.reservedStock = reservedStock + quantity;
    }

    public void defineCategory(UUID categoryId) {
        if(categoryId == null) {
            throw new InvalidProductCategoryException();
        }

         this.categoryId = categoryId;
    }
    public UUID getCategoryId() {
        return categoryId;
    }
    public boolean isOutOfStock() {
        return this.availableStock <= 0;
    }

    private boolean hasEnoughStock(int quantity) {
        return quantity > 0 && availableStock >= quantity;
    }
    public boolean isInactive() {
        return !this.isActive();
    }
    public boolean canSell(int quantity) {
        return isActive() && hasEnoughStock(quantity);
    }
    public static Product create(
            ProductName name,
            String description,
            Money price,
            Integer stock,
            UUID categoryId

    ) {
        Product product =  new Product();
        product.defineId(UUID.randomUUID());
        product.rename(name);
        product.changeDescription(description);
        product.changePrice(price);
        product.defineInitialStock(stock);
        product.activate();
        product.defineCreatedAt(LocalDateTime.now());
        product.defineCategory(categoryId);

        return product;
    }

}
