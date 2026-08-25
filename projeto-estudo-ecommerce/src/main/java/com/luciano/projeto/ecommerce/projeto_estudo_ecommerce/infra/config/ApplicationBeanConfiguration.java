package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.config;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.out.CategoryRepository;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.out.ProductRepository;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.service.CategoryService;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.service.ProductService;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.service.impl.CategoryServiceImpl;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.service.impl.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    CategoryService categoryService(CategoryRepository repository) {
        return new CategoryServiceImpl(repository);
    }

    @Bean
    ProductService productSerice(
            ProductRepository productRepository,
            CategoryRepository categoryRepository
    ) {
        return new ProductServiceImpl(productRepository, categoryRepository);
    }
}
