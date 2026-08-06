package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.service.impl;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.request.ProductRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.request.ProductUpdateRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.response.ProductResponse;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productnotfoundexception.CategoryNotFoundException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productnotfoundexception.ProductNotFoundException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Product;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.repository.CategoryRepository;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.repository.ProductRepository;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.service.ProductService;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final R2dbcEntityTemplate entityTemplate;
    ProductServiceImpl(
        ProductRepository productRepository,
        CategoryRepository categoryRepository,
        R2dbcEntityTemplate r2dbcEntityTemplate
    ){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.entityTemplate = r2dbcEntityTemplate;
    }
    @Override
    public Mono<ProductResponse> createProduct(ProductRequest request) {
         return categoryRepository.findById(request.categoryId())
                 .switchIfEmpty(Mono.error(
                         () -> new CategoryNotFoundException(request.categoryId()))
                 ).flatMap(category -> {
                        Product product = Product.create(
                                request.name(),
                                request.description(),
                                request.price(),
                                request.stock(),
                                request.categoryId()
                        );
                        return entityTemplate.insert(product);

                 }).map(ProductResponse::from);
    }

    @Override
    public Mono<ProductResponse> getProductById(UUID productId) {
        return productRepository.findById(productId).switchIfEmpty(
                Mono.error(() -> new ProductNotFoundException(productId))
        ).map(ProductResponse::from);
    }

    @Override
    public Flux<ProductResponse> getAllProducts() {
        return productRepository.findAll().map(ProductResponse::from);

    }
    @Override
    public Mono<ProductResponse> updateProduct(
            UUID productId,
            ProductUpdateRequest request) {

        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(() -> new ProductNotFoundException(productId))
                )
                .map(request::updateEntity)
                .flatMap(entityTemplate::update)
                .map(ProductResponse::from);
    }

    public Mono<Void> deleteProductById(UUID productId) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(() -> new ProductNotFoundException(productId)))
                .map(product -> {
                    product.deactivate();
                    return product; })
                .flatMap(entityTemplate::update)
                .then();
    }

}
