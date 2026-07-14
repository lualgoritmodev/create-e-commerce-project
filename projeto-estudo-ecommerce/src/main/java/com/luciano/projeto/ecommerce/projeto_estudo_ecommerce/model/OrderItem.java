package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Table(name = "tb_order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @Column(nullable = false)
    private Integer quantity;
    @NotNull
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal unitPrice;
    @NotNull
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public OrderItem() {}
    public OrderItem(UUID id, Order order,
                     Integer quantity,
                     BigDecimal unitPrice,
                     BigDecimal subtotal,
                     Product product) {
        this.id = id;
        this.order = order;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
        this.product = product;
    }
    public UUID getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }


}
