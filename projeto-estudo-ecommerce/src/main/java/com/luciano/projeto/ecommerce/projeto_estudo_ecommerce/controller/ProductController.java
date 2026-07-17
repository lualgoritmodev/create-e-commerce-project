package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.request.ProductRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.response.ProductResponse;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public Mono<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        return productService.createProduct(request);

    }
}
