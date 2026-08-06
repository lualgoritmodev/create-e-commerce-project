package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productbusinessexception;

public class BusinessException extends RuntimeException {
    BusinessException(String message) {
        super(message);
    }
}
