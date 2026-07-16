package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.service.impl;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.request.ProductRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.controller.dto.response.ProductResponse;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.Product;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.repository.CategoryRepository;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.repository.ProductRepository;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.service.ProductService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    ProductServiceImpl(
        ProductRepository productRepository,
        CategoryRepository categoryRepository
    ){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Mono<ProductResponse> createProduct(ProductRequest request) {
         return categoryRepository.findById(request.categoryId())
                 .switchIfEmpty(Mono.error(
                         new IllegalArgumentException(
                                 "Category Not Found"))
                 ).flatMap(category -> {
                        Product product = request.toEntity();
                        product.defineId(UUID.randomUUID());
                        product.changeActive(true);
                        product.defineCreatedAt(LocalDateTime.now());
                        return productRepository.save(product);
                 }).map(ProductResponse::from);
    }

}
