package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.exception.businessexception;

public class InsufficientStockException extends BusinessException {
    InsufficientStockException(String message) {
        super("Stack insufficient");
    }
}
