package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException() {
        super("Estoque insuficiente para realizar a reserva");
    }
}
