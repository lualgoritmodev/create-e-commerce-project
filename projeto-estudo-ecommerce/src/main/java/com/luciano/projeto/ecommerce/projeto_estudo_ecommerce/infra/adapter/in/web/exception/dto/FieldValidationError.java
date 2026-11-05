package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web.exception.dto;

public record FieldValidationError(
        String field,
        String message
) {
}
