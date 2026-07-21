package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.exception.response;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path

){}
