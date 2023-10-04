package com.example.controllers;

import com.example.models.Category;
import com.example.models.Goods;
import com.example.services.CategoryService;
import com.example.services.GoodsAttributesService;
import com.example.services.GoodsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GoodsControllerTest {
    @Mock
    private GoodsService goodsService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private GoodsAttributesService goodsAttributesService;

    @Mock
    private Model model;

    private GoodsController goodsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        goodsController = new GoodsController(goodsService, categoryService, goodsAttributesService);
    }

    @Test
    void testCatalog() {
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(new Goods());
        when(goodsService.findAll()).thenReturn(goodsList);

        String viewName = goodsController.catalog(model);

        assertEquals("goods/catalog", viewName);
        verify(goodsService, times(1)).findAll();
        verify(model, times(1)).addAttribute("allGoods", goodsList);
    }

    @Test
    void testCategory() {
        int categoryId = 1;
        Category category = new Category();
        List<Category> subcategories = new ArrayList<>();
        when(categoryService.getCategoryById(categoryId)).thenReturn(category);
        when(categoryService.getSubcategoriesByParentCategoryId(categoryId)).thenReturn(subcategories);

        String viewName = goodsController.category(model, categoryId);

        assertEquals("goods/category", viewName);
        verify(categoryService, times(1)).getCategoryById(categoryId);
        verify(categoryService, times(1)).getSubcategoriesByParentCategoryId(categoryId);
        verify(model, times(1)).addAttribute("category", category);
        verify(model, times(1)).addAttribute("subcategories", subcategories);
    }

    @Test
    void testCategoryGoods() {
        Long categoryId = 1L;
        List<Goods> goodsList = new ArrayList<>();
        when(goodsService.findByCategoryId(categoryId)).thenReturn(goodsList);

        String viewName = goodsController.categoryGoods(model, categoryId);

        assertEquals("goods/catalog", viewName);
        verify(goodsService, times(1)).findByCategoryId(categoryId);
        verify(model, times(1)).addAttribute("allGoods", goodsList);
    }

    @Test
    void testShow() {
        int goodsId = 1;
        Goods goods = new Goods();
        when(goodsService.findOne(goodsId)).thenReturn(goods);

        String viewName = goodsController.show(goodsId, model);

        assertEquals("goods/show", viewName);
        verify(goodsService, times(1)).findOne(goodsId);
        verify(model, times(1)).addAttribute("goods", goods);
    }

    @Test
    void testMain() {
        String viewName = goodsController.mainPage();

        assertEquals("/main", viewName);
    }

    @Test
    void testCatalogPage() {
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(new Goods());
        when(goodsService.findAll()).thenReturn(goodsList);

        String viewName = goodsController.catalogPage(model);

        assertEquals("goods/catalogPage", viewName);
        verify(goodsService, times(1)).findAll();
        verify(model, times(1)).addAttribute("allGoods", goodsList);
    }
}