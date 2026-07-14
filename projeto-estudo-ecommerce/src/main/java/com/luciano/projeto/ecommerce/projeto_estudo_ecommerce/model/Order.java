package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.utilenum.OrderStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "tb_order")
public class Order {
    @Id
    private UUID id;
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();
    @NotNull
    private BigDecimal total;
    @NotNull
    private OrderStatus status;
    @NotNull
    private Customer customer;

    private Payment payment;

    private List<OrderItem> items = new ArrayList<>();

    public Order(UUID id, LocalDateTime createdAt, BigDecimal total, OrderStatus status, Customer customer) {
        this.id = id;
        this.createdAt = createdAt;
        this.total = total;
        this.status = status;
        this.customer = customer;
    }

    public Order() {}

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void assignPayment(Payment payment) {

        if (payment == null) {
            throw new IllegalArgumentException("Payment cannot be null");
        }
        this.payment = payment;
        payment.setOrder(this);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }
}
