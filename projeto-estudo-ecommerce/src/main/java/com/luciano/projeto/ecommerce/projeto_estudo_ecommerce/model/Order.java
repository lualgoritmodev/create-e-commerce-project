package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.utilenum.OrderStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("tb_order")
public class Order {
    @Id
    private UUID id;
    @NotNull
    @Column("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @NotNull
    @Column("total")
    private BigDecimal total;
    @NotNull
    @Column("order_status")
    private OrderStatus status;
    @NotNull
    @Column("customer_id")
    private UUID customerId;

    public Order(UUID id, LocalDateTime createdAt,
                 BigDecimal total,
                 OrderStatus status,
                 UUID customerId) {
        this.id = id;
        this.createdAt = createdAt;
        this.total = total;
        this.status = status;
        this.customerId = customerId;
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

    public UUID getCustomerId() {
        return customerId;
    }

//    public void setCustomerId(UUID customerId) {
//        this.customerId = customerId;
//    }


//    public void assignPayment(UUID paymentId) {
//
//        if (paymentId == null) {
//            throw new IllegalArgumentException("Payment cannot be null");
//        }
//        this.paymentId = paymentId;
//        paymentId.setOrder(this);
//    }

}
