package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.utilenum.PaymentMethod;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.utilenum.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("tb_payment")
public class Payment {

    @Id
    private UUID id;
    @NotNull
    @Column("payment_method")
    private PaymentMethod method;
    @NotNull
    @Column("payment_status")
    private PaymentStatus status;
    @NotNull
    @Column("value")
    private BigDecimal value;
    @NotNull
    @Column("payment_date")
    private LocalDateTime paymentDate;
    @NotNull
    @Column("order_id")
    private UUID orderId;

    public Payment() {
    }

    public Payment(UUID id, PaymentMethod method,
                   PaymentStatus status,
                   BigDecimal value,
                   LocalDateTime paymentDate, UUID orderId) {
        this.id = id;
        this.method = method;
        this.status = status;
        this.value = value;
        this.paymentDate = paymentDate;
        this.orderId = orderId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOrderId() {
        return orderId;
    }

}
