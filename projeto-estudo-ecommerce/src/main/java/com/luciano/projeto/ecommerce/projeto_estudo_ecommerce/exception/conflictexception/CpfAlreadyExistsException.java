package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.exception.conflictexception;

public class CpfAlreadyExistsException extends ConflictException {
    CpfAlreadyExistsException(String message) {
        super(message);
    }
}
