package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web.exception.productbusinessexception;

public class InsufficientStockException extends BusinessException {
    InsufficientStockException(String message) {
        super("Stack insufficient");
    }
}
