package com.example.repositories;

import com.example.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category parentCategory;
    private Category childCategory1;
    private Category childCategory2;

    @BeforeEach
    void setUp() {
        parentCategory = new Category();
        parentCategory.setName("Parent Category");
        categoryRepository.save(parentCategory);

        childCategory1 = new Category();
        childCategory1.setName("Child Category 1");
        childCategory1.setParentCategory(parentCategory);
        categoryRepository.save(childCategory1);

        childCategory2 = new Category();
        childCategory2.setName("Child Category 2");
        childCategory2.setParentCategory(parentCategory);
        categoryRepository.save(childCategory2);
    }

    @Test
    void findByParentCategoryId_ExistingParentCategoryId_ReturnsMatchingCategories() {
        List<Category> categories = categoryRepository.findByParentCategoryId(parentCategory.getId());

        assertEquals(2, categories.size());
        assertTrue(categories.contains(childCategory1));
        assertTrue(categories.contains(childCategory2));
    }

    @Test
    void findByParentCategoryId_NotExistingParentCategoryId_ReturnsEmptyList() {
        List<Category> categories = categoryRepository.findByParentCategoryId(parentCategory.getId() + 1);

        assertTrue(categories.isEmpty());
    }

    @Test
    void findByParentCategoryIdNotNull_ExistingParentCategoryId_ReturnsCategoriesWithNonNullParentCategoryId() {
        Category childCategory3 = new Category();
        childCategory3.setName("Child Category 3");
        categoryRepository.save(childCategory3);

        List<Category> categories = categoryRepository.findByParentCategoryIdNotNull();

        assertEquals(20, categories.size());
        assertTrue(categories.contains(childCategory1));
        assertTrue(categories.contains(childCategory2));
    }

    @Test
    void findByParentCategoryIdNotNull_NoCategoriesWithParentCategoryId_ReturnsEmptyList() {
        List<Category> categories = categoryRepository.findByParentCategoryIdNotNull();

        assertFalse(categories.isEmpty());
    }
}
