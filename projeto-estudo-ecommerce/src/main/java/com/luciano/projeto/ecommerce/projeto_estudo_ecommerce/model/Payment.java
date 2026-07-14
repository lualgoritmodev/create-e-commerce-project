package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.utilenum.PaymentMethod;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.utilenum.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "tb_payment")
public class Payment {

    @Id
    private UUID id;
    @NotNull
    private PaymentMethod method;
    @NotNull
    private PaymentStatus status;
    @NotNull
    private BigDecimal value;
    @NotNull
    private LocalDateTime paymentDate;

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
