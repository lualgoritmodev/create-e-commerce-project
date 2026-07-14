package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.utilenum.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @NotNull
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal total;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "payment_id", unique = true)
    private Payment payment;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
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
