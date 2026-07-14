package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Table(name = "tb_category")
public class Category {
    @Id
    private UUID id;
    @NotBlank
    private String name;
    @NotNull
    private Boolean active = true;
    private List<Product> products = new ArrayList<>();

    public Category() {}
    public Category(UUID id, String name, Boolean active, List<Product> products) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.products = products;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }
}
