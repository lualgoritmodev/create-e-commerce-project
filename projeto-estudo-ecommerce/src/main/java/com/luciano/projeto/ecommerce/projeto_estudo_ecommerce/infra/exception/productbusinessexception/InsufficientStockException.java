package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productbusinessexception;

public class InsufficientStockException extends BusinessException {
    InsufficientStockException(String message) {
        super("Stack insufficient");
    }
}
