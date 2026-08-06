package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.response.ApiErrorResponse;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productnotfoundexception.ResourceNotFoundException;
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
                status.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                message,
                exchange.getRequest().getPath().value()
        );
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFound(
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
