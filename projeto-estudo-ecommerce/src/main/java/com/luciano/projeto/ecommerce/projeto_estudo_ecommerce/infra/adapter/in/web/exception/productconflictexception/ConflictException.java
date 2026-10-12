package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web.exception.productconflictexception;

public class ConflictException extends RuntimeException {

    ConflictException(String message) {
        super(message);
    }
}
