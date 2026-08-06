package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.util.UUID;
@Table("tb_order_item")
public class OrderItem {
    @Id
    private UUID id;
    @NotNull
    @Column("quantity")
    private Integer quantity;
    @NotNull
    @Column("unit_price")
    private BigDecimal unitPrice;
    @NotNull
    @Column("sub_total")
    private BigDecimal subtotal;
    @NotNull
    @Column("order_id")
    private UUID orderId;
    @NotNull
    @Column("product_id")
    private UUID productId;

    public OrderItem() {}

    public OrderItem(UUID id, UUID orderId,
                     Integer quantity,
                     BigDecimal unitPrice,
                     BigDecimal subtotal,
                     UUID productId) {
        this.id = id;
        this.orderId = orderId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
        this.productId = productId;
    }
    public UUID getId() {
        return id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrder(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProduct(UUID productId) {
        this.productId = productId;
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
