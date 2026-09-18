package application.service;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.out.CategoryRepository;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.service.impl.CategoryServiceImpl;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.CategoryNameAlreadyExistsException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.Category;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.CategoryName;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.request.CategoryRequest;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.request.RenameCategory;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.response.CategoryResponse;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productnotfoundexception.CategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.eq;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryServiceImpl categoryServiceImpl;

    @BeforeEach
    void setUp() {
        this.categoryServiceImpl = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void shouldFindCategoryById() {
        UUID id = UUID.randomUUID();
        Category category = Category.rehydrate(id, new CategoryName("Gamer"), true);

        when(categoryRepository.findById(id)).thenReturn(Mono.just(category));

        Mono<CategoryResponse> response = categoryServiceImpl.findById(id);

        StepVerifier.create(response).assertNext(categoryResponse -> {
            assertEquals(id, categoryResponse.id());
            assertEquals("Gamer", categoryResponse.name());
            assertTrue(categoryResponse.isEnabled());
        }).verifyComplete();

    }

    @Test
    void shouldThrowCategoryNotFoundExceptionWhenCategoryDoesNotExist() {

        UUID id = UUID.randomUUID();
        when(categoryRepository.findById(id)).thenReturn(Mono.empty());
        Mono<CategoryResponse> response = categoryServiceImpl.findById(id);

        StepVerifier.create(response)
                .expectError(CategoryNotFoundException.class).verify();
    }

    @Test
    void shouldCreateCategoryWhenNameDoesNotExist() {

        CategoryRequest categoryRequest = new CategoryRequest("Gamer");

        when(categoryRepository.existsByName(new CategoryName("Gamer")))
                .thenReturn(Mono.just(false));


        when(categoryRepository.save(any(Category.class))).thenAnswer(invocation ->
                Mono.just(invocation.getArgument(0))
        );

        Mono<CategoryResponse> categoryResponse =
                categoryServiceImpl.createCategory(categoryRequest);

        StepVerifier.create(categoryResponse).assertNext(response -> {
            assertEquals("Gamer", response.name());
            assertNotNull(response.id());
            assertTrue(response.isEnabled());
        }).verifyComplete();

    }

    @Test
    void shouldThrowExceptionWhenCategoryNameAlreadyExists() {
        CategoryRequest categoryRequest = new CategoryRequest("Gamer");

        when(categoryRepository.existsByName(new CategoryName("Gamer")))
                .thenReturn(Mono.just(true));

        Mono<CategoryResponse> categoryResponse =
                categoryServiceImpl.createCategory(categoryRequest);

        StepVerifier.create(categoryResponse)
                .expectError(CategoryNameAlreadyExistsException.class)
                .verify();

        verify(categoryRepository, never()).save(any(Category.class));

    }

    @Test
    void shouldRenameCategoryWhenNameIsAvailable() {
        String jogos = "Jogos";

        UUID idCategory = UUID.randomUUID();

        Category category = Category.rehydrate(
                idCategory,
                new CategoryName("Gamer"),
                true
        );

        when(categoryRepository.findById(idCategory)).thenReturn(Mono.just(category));

        when(categoryRepository.existsByNameAndIdNot(new CategoryName(jogos),
                idCategory))
                .thenReturn(Mono.just(false));

        RenameCategory renameCategory = new RenameCategory(idCategory, jogos);

        when(categoryRepository.save(any(Category.class)))
                .thenAnswer( invacation ->
                Mono.just(invacation.getArgument(0)));

        Mono<RenameCategory> renameCategoryResponse = categoryServiceImpl.renameCategory(
                idCategory, renameCategory);

        StepVerifier.create(renameCategoryResponse).assertNext( response -> {
           assertEquals(idCategory, response.id());
           assertEquals(jogos, response.name());
        }).verifyComplete();

        verify(categoryRepository, times(1))
                .existsByNameAndIdNot(any(CategoryName.class), eq(idCategory));
        verify(categoryRepository, times(1))
                .save(any(Category.class));
    }

    @Test
    void shouldThrowCategoryNotFoundExceptionWhenRenamingNonExistingCategory() {
        UUID id = UUID.randomUUID();
        RenameCategory renameCategory = new RenameCategory(id, "Jogos");

        when(categoryRepository.findById(id)).thenReturn(Mono.empty());

        Mono<RenameCategory> response = categoryServiceImpl.renameCategory(id,
                renameCategory);

        StepVerifier.create(response)
                .expectError(CategoryNotFoundException.class).verify();
    }

    @Test
    void shouldThrowCategoryNotFoundExceptionWhenEnablingNonExistingCategory() {
        UUID id = UUID.randomUUID();

        when(categoryRepository.findById(id)).thenReturn(Mono.empty());

        Mono<Void> response = categoryServiceImpl.enableCategory(id);

        StepVerifier.create(response).expectError(CategoryNotFoundException.class).verify();

        verify(categoryRepository).findById(any());
        verify(categoryRepository, never()).save(any(Category.class));

    }

    @Test
    void shouldThrowCategoryNotFoundExceptionWhenDisablingNonExistingCategory() {
        UUID id = UUID.randomUUID();

        when(categoryRepository.findById(id)).thenReturn(Mono.empty());

        Mono<Void> response = categoryServiceImpl.disableCategory(id);

        StepVerifier.create(response).expectError(CategoryNotFoundException.class)
                .verify();

        verify(categoryRepository).findById(id);
        verify(categoryRepository,never()).save(any(Category.class));

    }

    @Test
    void shouldThrowExceptionWhenRenamingToExistingCategoryName() {
        UUID id = UUID.randomUUID();
        RenameCategory renameCategory = new RenameCategory(id, "Jogos");
        Category category = Category.rehydrate(id, new CategoryName("Gamer"), true);

        when(categoryRepository.findById(id)).thenReturn(Mono.just(category));
        when(categoryRepository.existsByNameAndIdNot(new CategoryName("Jogos"), id))
                .thenReturn(Mono.just(true));

        Mono<RenameCategory> response = categoryServiceImpl.renameCategory(id,
                renameCategory);

        StepVerifier.create(response)
                .expectError(CategoryNameAlreadyExistsException.class)
                .verify();

        verify(categoryRepository, never()).save(any(Category.class));

    }

    @Test
    void shouldReturnCategoryWithoutSavingWhenNameIsUnchanged() {
        UUID id = UUID.randomUUID();
        Category category = Category.rehydrate(id, new CategoryName("Jogos"), true);
        RenameCategory renameCategory = new RenameCategory(id, "Jogos");

        when(categoryRepository.findById(id)).thenReturn(Mono.just(category));

        Mono<RenameCategory> response = categoryServiceImpl.renameCategory(id,
                renameCategory);

        StepVerifier.create(response).assertNext(res -> {
            assertEquals(category.getName().value(), res.name());
            assertEquals(category.getId(), res.id());
        }).verifyComplete();

        verify(categoryRepository, times(1)).findById(any());
        verify(categoryRepository, never()).save(any());
    }

    @Test
    void shouldEnableCategoryWhenDisabled() {
        UUID id = UUID.randomUUID();
        Category category = Category.rehydrate(id, new CategoryName("Jogos"), false);
        ArgumentCaptor<Category> captor = ArgumentCaptor.forClass(Category.class);

        when(categoryRepository.findById(id)).thenReturn(Mono.just(category));
        when(categoryRepository.save(any())).thenReturn(Mono.just(category));

        Mono<Void> response = categoryServiceImpl.enableCategory(id);

        StepVerifier.create(response).verifyComplete();

        verify(categoryRepository).findById(id);
        verify(categoryRepository).save(captor.capture());

        Category saveCategory = captor.getValue();
        assertTrue(saveCategory.isEnabled());
    }

    @Test
    void shouldDisableCategoryWhenEnabled() {

        UUID id = UUID.randomUUID();
        Category category = Category.rehydrate(id, new CategoryName("Jogos"), true);
        ArgumentCaptor<Category> captor = ArgumentCaptor.forClass(Category.class);

        when(categoryRepository.findById(id)).thenReturn(Mono.just(category));
        when(categoryRepository.save(any())).thenReturn(Mono.just(category));

        Mono<Void> response = categoryServiceImpl.disableCategory(category.getId());

        StepVerifier.create(response).verifyComplete();

        verify(categoryRepository).findById(id);
        verify(categoryRepository).save(captor.capture());

        Category saveCategory = captor.getValue();
        assertFalse(saveCategory.isEnabled());

    }

    @Test
    void shouldFindAllEnabledCategories() {
        UUID idGames = UUID.randomUUID();
        UUID idBooks = UUID.randomUUID();
        Category category = Category.rehydrate(idGames, new CategoryName("Jogos"), true);
        Category categoryC = Category.rehydrate(idBooks, new CategoryName("Biblioteca"), true);

        when(categoryRepository.findAllEnabled()).thenReturn(Flux.just(category,
                categoryC));

        Flux<CategoryResponse> response = categoryServiceImpl.findAllEnabled();

        StepVerifier.create(response).assertNext(categoryResponse -> {
            assertEquals(category.getId(), categoryResponse.id());
            assertEquals("Jogos", categoryResponse.name());
            assertTrue(categoryResponse.isEnabled());

        }).assertNext(categoryResponse -> {
            assertEquals(categoryC.getId(), categoryResponse.id());
            assertEquals(categoryC.getName().value(), categoryResponse.name());
            assertTrue(categoryResponse.isEnabled());
        }).verifyComplete();

        verify(categoryRepository).findAllEnabled();

    }

    @Test
    void shouldFindAllDisabledCategories() {
        UUID idGames = UUID.randomUUID();
        UUID idBooks = UUID.randomUUID();
        Category category = Category.rehydrate(idGames, new CategoryName("Jogos"), false);
        Category categoryC = Category.rehydrate(idBooks, new CategoryName("Biblioteca")
                , false);

        when(categoryRepository.findAllDisabled()).thenReturn(Flux.just(category,
                categoryC));

        Flux<CategoryResponse> response = categoryServiceImpl.findAllDisabled();

        StepVerifier.create(response).assertNext(categoryResponse -> {
            assertEquals(category.getId(), categoryResponse.id());
            assertEquals("Jogos", categoryResponse.name());
            assertFalse(categoryResponse.isEnabled());

        }).assertNext(categoryResponse -> {
            assertEquals(categoryC.getId(), categoryResponse.id());
            assertEquals(categoryC.getName().value(), categoryResponse.name());
            assertFalse(categoryResponse.isEnabled());
        }).verifyComplete();

        verify(categoryRepository).findAllDisabled();

    }

    @Test
    void shouldReturnEmptyFluxWhenThereAreNoEnabledCategories() {

        when(categoryRepository.findAllEnabled()).thenReturn(Flux.empty());

        Flux<CategoryResponse> response = categoryServiceImpl.findAllEnabled();

        StepVerifier.create(response).verifyComplete();

        verify(categoryRepository).findAllEnabled();
    }

    @Test
    void shouldReturnEmptyFluxWhenThereAreNoDisabledCategories() {

        when(categoryRepository.findAllDisabled()).thenReturn(Flux.empty());

        Flux<CategoryResponse> response = categoryServiceImpl.findAllDisabled();

        StepVerifier.create(response).verifyComplete();

        verify(categoryRepository).findAllDisabled();
    }

    @Test
    void shouldFindAllCategories() {

        UUID idGames = UUID.randomUUID();
        UUID idBooks = UUID.randomUUID();
        Category category = Category.rehydrate(idGames, new CategoryName("Jogos"), true);
        Category categoryC = Category.rehydrate(idBooks, new CategoryName("Biblioteca")
                , false);

        when(categoryRepository.findAllCategories()).thenReturn(Flux.just(category,
                categoryC));
        Flux<CategoryResponse> response = categoryServiceImpl.findAllCategories();

        StepVerifier.create(response).assertNext(categoryResponse -> {
            assertEquals(category.getId(), categoryResponse.id());
            assertEquals(category.getName().value(), categoryResponse.name());
            assertTrue(categoryResponse.isEnabled());
        }).assertNext(categoryResponse -> {
            assertEquals(categoryC.getId(), categoryResponse.id());
            assertEquals(categoryC.getName().value(), categoryResponse.name());
            assertFalse(categoryResponse.isEnabled());
        }).verifyComplete();

        verify(categoryRepository).findAllCategories();

    }

    @Test
    void shouldReturnEmptyFluxWhenThereAreNoCategories() {

        when(categoryRepository.findAllCategories()).thenReturn(Flux.empty());

        Flux<CategoryResponse> response = categoryServiceImpl.findAllCategories();

        StepVerifier.create(response).verifyComplete();

        verify(categoryRepository).findAllCategories();
    }

}
