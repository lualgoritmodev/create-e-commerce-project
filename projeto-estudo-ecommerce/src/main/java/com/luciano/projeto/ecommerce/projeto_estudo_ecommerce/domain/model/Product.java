package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InsufficientStockException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidNegativeQuantityException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidProductCategoryException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidProductDescriptionException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidProductNameException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidProductPriceException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidQuantityException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.Money;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.ProductName;

import java.time.LocalDateTime;
import java.util.UUID;

public class Product {

    private UUID id;
    private ProductName name;
    private String description;
    private Money price;
    private boolean isActive;
    private LocalDateTime createdAt;
    private int availableStock;
    private int reservedStock;
    private UUID categoryId;

    private Product() {
    }

    // =========================
    // Queries / leitura
    // =========================

    public UUID getId() {
        return id;
    }

    public ProductName getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Money getPrice() {
        return price;
    }

    public boolean isActive() {
        return isActive;
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

    public boolean isInactive() {
        return !isActive;
    }

    public boolean isOutOfStock() {
        return availableStock == 0;
    }

    public boolean canSell(int quantity) {
        return isActive && hasEnoughStock(quantity);
    }

    // =========================
    // Comportamentos públicos
    // =========================

    public void rename(ProductName newName) {
        if (newName == null) {
            throw new InvalidProductNameException();
        }

        this.name = newName;
    }

    public void changeDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new InvalidProductDescriptionException();
        }

        this.description = description;
    }

    public void changePrice(Money price) {
        if (price == null || !price.isPositive()) {
            throw new InvalidProductPriceException();
        }

        this.price = price;
    }

    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void addStock(int quantity) {
        validatePositiveQuantity(quantity);

        this.availableStock += quantity;
    }

    public void reserveStock(int quantity) {
        validatePositiveQuantity(quantity);

        if (!hasEnoughStock(quantity)) {
            throw new InsufficientStockException();
        }

        this.availableStock -= quantity;
        this.reservedStock += quantity;
    }

    // =========================
    // Regras internas
    // =========================

    private boolean hasEnoughStock(int quantity) {
        return quantity > 0 && availableStock >= quantity;
    }

    private void validateNonNegativeQuantity(int quantity) {
        if (quantity < 0) {
            throw new InvalidNegativeQuantityException();
        }
    }

    private void validatePositiveQuantity(int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException();
        }
    }

    // =========================
    // Estado de criação
    // =========================

    private void defineInitialStock(int quantity) {
        validateNonNegativeQuantity(quantity);

        this.availableStock = quantity;
        this.reservedStock = 0;
    }

    private void defineCategory(UUID categoryId) {
        if (categoryId == null) {
            throw new InvalidProductCategoryException();
        }

        this.categoryId = categoryId;
    }

    // =========================
    // Estado de reidratação
    // =========================

    private void restoreStock(
            int availableStock,
            int reservedStock
    ) {
        validateNonNegativeQuantity(availableStock);
        validateNonNegativeQuantity(reservedStock);

        this.availableStock = availableStock;
        this.reservedStock = reservedStock;
    }

    // =========================
    // Factory - novo Product
    // =========================

    public static Product create(
            ProductName name,
            String description,
            Money price,
            int initialStock,
            UUID categoryId
    ) {
        Product product = new Product();

        product.id = UUID.randomUUID();
        product.rename(name);
        product.changeDescription(description);
        product.changePrice(price);
        product.defineInitialStock(initialStock);
        product.activate();
        product.createdAt = LocalDateTime.now();
        product.defineCategory(categoryId);

        return product;
    }

    // =========================
    // Factory - reidratação
    // =========================

    public static Product rehydrate(
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
        Product product = new Product();

        product.id = id;
        product.rename(name);
        product.changeDescription(description);
        product.changePrice(price);
        product.isActive = active;
        product.createdAt = createdAt;
        product.restoreStock(
                availableStock,
                reservedStock
        );
        product.defineCategory(categoryId);

        return product;
    }

}
