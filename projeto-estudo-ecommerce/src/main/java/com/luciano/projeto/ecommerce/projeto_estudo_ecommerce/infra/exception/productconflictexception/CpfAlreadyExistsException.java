package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productconflictexception;

public class CpfAlreadyExistsException extends ConflictException {
    CpfAlreadyExistsException(String message) {
        super(message);
    }
}
