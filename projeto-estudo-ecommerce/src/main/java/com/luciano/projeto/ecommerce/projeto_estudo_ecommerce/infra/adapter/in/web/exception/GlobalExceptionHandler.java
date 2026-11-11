package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web.exception;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.exception.CategoryNameAlreadyExistsException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.exception.CategoryNotFoundException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidCategoryNameException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web.exception.dto.FieldValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;
import java.net.URI;
import java.util.List;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    private ProblemDetail createProblemDetail(
            HttpStatus status,
            String title,
            String detail,
            String code,
            ServerWebExchange exchange
    ) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                status,
                detail
        );

        problem.setTitle(title);
        problem.setInstance(
                URI.create(exchange.getRequest().getPath().value())
        );
        problem.setProperty("code", code);

        return problem;
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ProblemDetail> handleValidation(
            WebExchangeBindException exception,
            ServerWebExchange exchange
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        List<FieldValidationError> errors = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldValidationError(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        ProblemDetail problem = createProblemDetail(
                status,
                "Validation error",
                "One or more fields are invalid",
                "VALIDATION_ERROR",
                exchange
        );

        problem.setProperty("errors", errors);

        return ResponseEntity.status(status)
                .body(problem);
    }

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<ProblemDetail> handleInvalidRequest(
            ServerWebInputException exception,
            ServerWebExchange exchange
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ProblemDetail problem = createProblemDetail(
                status,
                "Invalid request",
                "The request could not be read or converted",
                "INVALID_REQUEST",
                exchange
        );

        return ResponseEntity.status(status)
                .body(problem);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleCategoryNotFound(
            CategoryNotFoundException exception,
            ServerWebExchange exchange
    ) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        ProblemDetail problem =  createProblemDetail(
                status,
                "Category not found",
                exception.getMessage(),
                "CATEGORY_NOT_FOUND",
                exchange
        );

        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(CategoryNameAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleCategoryNameAlreadyExists(
            CategoryNameAlreadyExistsException exception,
            ServerWebExchange exchange
    ) {

        HttpStatus status = HttpStatus.CONFLICT;

        ProblemDetail problem =  createProblemDetail(
                status,
                "Category name already exists",
                exception.getMessage(),
                "CATEGORY_NAME_ALREADY_EXISTS",
                exchange
        );

         return ResponseEntity.status(status).body(problem);

    }

    @ExceptionHandler(InvalidCategoryNameException.class)
    public ResponseEntity<ProblemDetail> handleInvalidCategoryName(
            InvalidCategoryNameException exception,
            ServerWebExchange exchange
    ) {

        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;

        ProblemDetail problem = createProblemDetail(
                status,
                "Invalid category name",
                exception.getMessage(),
                "INVALID_CATEGORY_NAME",
                exchange
        );

        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ProblemDetail> handleResponseStatusException(
            ResponseStatusException exception,
            ServerWebExchange exchange
    ) {
        HttpStatus status = HttpStatus.valueOf(
                exception.getStatusCode().value()
        );

        if (status.is5xxServerError()) {
            LOGGER.error(
                    "Spring WebFlux error while processing request path={}",
                    exchange.getRequest().getPath().value(),
                    exception
            );
        }

        ProblemDetail problem = createProblemDetail(
                status,
                status.getReasonPhrase(),
                "The request could not be processed",
                status.name(),
                exchange
        );

        return ResponseEntity.status(status)
                .headers(exception.getHeaders())
                .body(problem);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleUnexpectedException(
            Exception exception,
            ServerWebExchange exchange
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        LOGGER.error(
                "Unexpected error while processing request path={}",
                exchange.getRequest().getPath().value(),
                exception
        );

        ProblemDetail problem = createProblemDetail(
                status,
                "Internal server error",
                "An unexpected error occurred",
                "INTERNAL_SERVER_ERROR",
                exchange
        );

        return ResponseEntity.status(status).body(problem);

    }

}
