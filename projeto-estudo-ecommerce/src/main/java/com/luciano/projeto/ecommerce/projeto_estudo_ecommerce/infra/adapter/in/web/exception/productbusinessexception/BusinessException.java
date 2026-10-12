package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web.exception.productbusinessexception;

public class BusinessException extends RuntimeException {
    BusinessException(String message) {
        super(message);
    }
}
