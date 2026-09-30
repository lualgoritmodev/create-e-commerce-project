package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.in.CategoryUseCase;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.dto.request.CategoryRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.dto.response.CategoryResponse;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.mapper.CategoryWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.CacheControl;
import java.time.Duration;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryUseCase categoryUseCase;
    private final CategoryWebMapper categoryWebMapper;

    public CategoryController(CategoryUseCase categoryUseCase, CategoryWebMapper categoryWebMapper) {
        this.categoryUseCase = categoryUseCase;
        this.categoryWebMapper = categoryWebMapper;
    }

    @PostMapping
    public Mono<ResponseEntity<CategoryResponse>> createCategory(
            @Valid @RequestBody CategoryRequest categoryRequest,
            ServerHttpRequest request) {

        return categoryUseCase.createCategory(
                categoryWebMapper.toCreateCommand(categoryRequest)
        ).map(categoryWebMapper::toResponse)
                .map(response -> {
                    URI location = UriComponentsBuilder
                            .fromUri(request.getURI())
                            .replaceQuery(null)
                            .path("/{id}")
                            .buildAndExpand(response.id())
                            .toUri();

                    return ResponseEntity.created(location).body(response);
                });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CategoryResponse>> findById(
            @PathVariable UUID id) {
         return categoryUseCase.findById(id)
                .map(categoryWebMapper::toResponse)
                 .map(ResponseEntity::ok);

    }

    @GetMapping("/disabled")
    public ResponseEntity<Flux<CategoryResponse>> findAllDisabled() {
         Flux<CategoryResponse> categories = categoryUseCase.findAllDisabled()
                .map(categoryWebMapper::toResponse);

         return ResponseEntity.ok()
                 .cacheControl(CacheControl.noStore())
                 .body(categories);

    }

    @GetMapping("/enabled")
    public ResponseEntity<Flux<CategoryResponse>> findAllEnabled() {
        Flux<CategoryResponse> categories = categoryUseCase.findAllEnabled()
                .map(categoryWebMapper::toResponse);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(Duration.ofMinutes(1))
                        .cachePublic())
                .body(categories);
    }

    @PatchMapping("/{id}/name")
    public Mono<ResponseEntity<CategoryResponse>> renameCategory(
             @PathVariable UUID id, @Valid @RequestBody CategoryRequest request) {

        return categoryUseCase.renameCategory(id,
                        categoryWebMapper.toRenameCategory(request))
                .map(categoryWebMapper::toResponse)
                .map(ResponseEntity::ok);

    }

    @PatchMapping("/{id}/enable")
    public Mono<ResponseEntity<Void>> enableCategory(@PathVariable UUID id) {

        return categoryUseCase.enableCategory(id)
                .thenReturn(ResponseEntity.noContent().build());

    }

    @PatchMapping("/{id}/disable")
    public Mono<ResponseEntity<Void>> disableCategory(@PathVariable UUID id) {

        return categoryUseCase.disableCategory(id)
                .thenReturn(ResponseEntity.noContent().build());

    }

    @GetMapping()
    public ResponseEntity<Flux<CategoryResponse>> findAllCategories() {

        Flux<CategoryResponse> category =  categoryUseCase.findAllCategories()
                .map(categoryWebMapper::toResponse);

        return ResponseEntity.ok()
                .body(category);

    }

}
