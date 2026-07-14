package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.utilenum.PaymentMethod;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.utilenum.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod method;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;
    @NotNull
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal value;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @OneToOne(mappedBy = "payment")
    private Order order;

    public Payment() {
    }

    public Payment(UUID id, PaymentMethod method,
                   PaymentStatus status,
                   BigDecimal value,
                   LocalDateTime paymentDate, Order order) {
        this.id = id;
        this.method = method;
        this.status = status;
        this.value = value;
        this.paymentDate = paymentDate;
        this.order = order;
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
}
