package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.service.impl;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.Product;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.repository.ProductRepository;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.service.ProductService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    ProductServiceImpl(
        ProductRepository productRepository
    ){
        this.productRepository = productRepository;
    }
    @Override
    public Mono<Product> createProduct(Product product) {
        return null;
    }

}
