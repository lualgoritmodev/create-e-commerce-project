package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.service.impl;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.out.ProductRepository;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.Money;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.ProductName;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.request.ProductRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.request.ProductUpdateRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.response.ProductResponse;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productnotfoundexception.CategoryNotFoundException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productnotfoundexception.ProductNotFoundException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Product;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.out.CategoryRepository;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

   public ProductServiceImpl(
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
                         () -> new CategoryNotFoundException(request.categoryId()))
                 ).flatMap(category -> {
                        Product product = Product.create(
                                new ProductName(request.name()),
                                request.description(),
                                new Money(request.price()),
                                request.stock(),
                                request.categoryId()
                        );
                        return productRepository.save(product);

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
                .flatMap(productRepository::save)
                .map(ProductResponse::from);
    }

    public Mono<Void> deleteProductById(UUID productId) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(() -> new ProductNotFoundException(productId)))
                .map(product -> {
                    product.deactivate();
                    return product; })
                .flatMap(productRepository::save)
                .then();
    }

}
