package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web.exception;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.exception.CategoryNameAlreadyExistsException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.exception.CategoryNotFoundException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web.exception.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    private ApiErrorResponse buildErrorResponse(
//            HttpStatus status,
//            String message,
//            ServerWebExchange exchange
//    ) {
//        return new ApiErrorResponse(
//                LocalDateTime.now(),
//                status.value(),
//                status.getReasonPhrase(),
//                message,
//                exchange.getRequest().getPath().value()
//        );
//    }

    private ResponseEntity<ProblemDetail> buildProblem(
            HttpStatus status,
            String title,
            String code,
            Exception exception,
            ServerWebExchange exchange
    ) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                status,
                exception.getMessage()
        );

        problem.setTitle(title);
        problem.setInstance(
                URI.create(exchange.getRequest().getPath().value())
        );
        problem.setProperty("code", code);

        return ResponseEntity.status(status)
                .body(problem);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleCategoryNotFound(
            CategoryNotFoundException exception,
            ServerWebExchange exchange
    ) {

        return buildProblem(
                HttpStatus.NOT_FOUND,
                "Category not found",
                "CATEGORY_NOT_FOUND",
                exception,
                exchange
        );

    }

    @ExceptionHandler(CategoryNameAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleCategoryNameAlreadyExists(
            CategoryNameAlreadyExistsException exception,
            ServerWebExchange exchange
    ) {
        return buildProblem(
                HttpStatus.CONFLICT,
                "Category name already exists",
                "CATEGORY_NAME_ALREADY_EXISTS",
                exception,
                exchange
        );
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
