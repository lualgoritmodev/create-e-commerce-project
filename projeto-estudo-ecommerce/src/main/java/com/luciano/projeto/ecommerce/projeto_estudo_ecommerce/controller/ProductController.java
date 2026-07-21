package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.request.ProductRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.response.ProductResponse;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public Mono<ResponseEntity<ProductResponse>> createProduct(
            @Valid @RequestBody ProductRequest request) {
        return productService.createProduct(request)
                .map(response ->
                        ResponseEntity.status(HttpStatus.CREATED)
                                .body(response));

    }
    @GetMapping("/product/{productId}")
    public Mono<ResponseEntity<ProductResponse>> getProductById(
            @PathVariable UUID productId
    ) {
        return productService.getProductById(productId)
                .map(response -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(response));
    }
    @GetMapping("/all")
    public Flux<ResponseEntity<Flux<ProductResponse>>> getAllProducts() {
        return Flux.just(
                ResponseEntity.ok(
                        productService.getAllProducts()
                )
        );
    }

}
