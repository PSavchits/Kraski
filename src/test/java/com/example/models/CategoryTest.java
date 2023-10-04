package com.example.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    void categoryConstructorTest() {
        long id = 1;
        String name = "Electronics";
        Category parentCategory = new Category();
        List<Category> subcategories = new ArrayList<>();
        List<Goods> goodsList = new ArrayList<>();

        Category category = new Category(id, name, parentCategory, subcategories, goodsList);

        assertEquals(id, category.getId());
        assertEquals(name, category.getName());
        assertEquals(parentCategory, category.getParentCategory());
        assertEquals(subcategories, category.getSubcategories());
        assertEquals(goodsList, category.getGoodsList());
    }

    @Test
    void settersAndGettersTest() {
        Category category = new Category();

        Long id = 1L;
        String name = "Electronics";
        Category parentCategory = new Category();
        List<Category> subcategories = new ArrayList<>();
        List<Goods> goodsList = new ArrayList<>();

        category.setId(id);
        category.setName(name);
        category.setParentCategory(parentCategory);
        category.setSubcategories(subcategories);
        category.setGoodsList(goodsList);

        assertEquals(id, category.getId());
        assertEquals(name, category.getName());
        assertEquals(parentCategory, category.getParentCategory());
        assertEquals(subcategories, category.getSubcategories());
        assertEquals(goodsList, category.getGoodsList());
    }
}