package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web.exception;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.exception.CategoryNotFoundException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web.exception.response.ApiErrorResponse;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web.exception.productnotfoundexception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ApiErrorResponse buildErrorResponse(
            HttpStatus status,
            String message,
            ServerWebExchange exchange
    ) {
        return new ApiErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                exchange.getRequest().getPath().value()
        );
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleCategoryNotFound(
            ResourceNotFoundException exception,
            ServerWebExchange exchange
    ) {
        ApiErrorResponse response =  buildErrorResponse(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                exchange
        );

        return
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(response);
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public Mono<ResponseEntity<ApiErrorResponse>>handleProductKotFound(
//            ProductNotFoundException exception,
//            ServerWebExchange exchange
//    ) {
//        ApiErrorResponse response = new ApiErrorResponse(
//                LocalDateTime.now(),
//                HttpStatus.NOT_FOUND.value(),
//                HttpStatus.NOT_FOUND.getReasonPhrase(),
//                exception.getMessage(),
//                exchange.getRequest().getPath().value()
//        );
//
//        return Mono.just(
//                ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(response)
//        );

//    }
}
