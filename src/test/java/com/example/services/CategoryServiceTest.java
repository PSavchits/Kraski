package com.example.services;

import com.example.models.Category;
import com.example.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    void getSubcategoriesByParentCategoryId_ShouldReturnSubcategories() {
        // Arrange
        int parentCategoryId = 1;
        List<Category> expectedSubcategories = new ArrayList<>();
        expectedSubcategories.add(new Category());
        expectedSubcategories.add(new Category());

        when(categoryRepository.findByParentCategoryId(parentCategoryId)).thenReturn(expectedSubcategories);

        // Act
        List<Category> actualSubcategories = categoryService.getSubcategoriesByParentCategoryId(parentCategoryId);

        // Assert
        assertThat(actualSubcategories).isEqualTo(expectedSubcategories);
        verify(categoryRepository).findByParentCategoryId(parentCategoryId);
    }

    @Test
    void getCategoryById_WithValidId_ShouldReturnCategory() {
        // Arrange
        int categoryId = 1;
        Category expectedCategory = new Category();
        expectedCategory.setId((long)categoryId);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(expectedCategory));

        // Act
        Category actualCategory = categoryService.getCategoryById(categoryId);

        // Assert
        assertThat(actualCategory).isEqualTo(expectedCategory);
        verify(categoryRepository).findById(categoryId);
    }

    @Test
    void getCategoryById_WithInvalidId_ShouldReturnNull() {
        // Arrange
        int categoryId = 1;

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act
        Category actualCategory = categoryService.getCategoryById(categoryId);

        // Assert
        assertThat(actualCategory).isNull();
        verify(categoryRepository).findById(categoryId);
    }

    @Test
    void getParentCategories_ShouldReturnParentCategories() {
        // Arrange
        List<Category> expectedParentCategories = new ArrayList<>();
        expectedParentCategories.add(new Category());
        expectedParentCategories.add(new Category());

        when(categoryRepository.findByParentCategoryIdNotNull()).thenReturn(expectedParentCategories);

        // Act
        List<Category> actualParentCategories = categoryService.getParentCategories();

        // Assert
        assertThat(actualParentCategories).isEqualTo(expectedParentCategories);
        verify(categoryRepository).findByParentCategoryIdNotNull();
    }
}