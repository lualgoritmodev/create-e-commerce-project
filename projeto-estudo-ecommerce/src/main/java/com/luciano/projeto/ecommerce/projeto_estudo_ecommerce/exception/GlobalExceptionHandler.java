package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.exception;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.exception.dtoerrorresponse.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CategoryNotFoundException.class)
    public Mono<ResponseEntity<ApiErrorResponse>> handleCategoryNotFound(
            CategoryNotFoundException exception,
            ServerWebExchange exchange
    ) {
        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                exception.getMessage(),
                exchange.getRequest().getPath().value()
        );

        return Mono.just(
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(response)
        );
    }
}
