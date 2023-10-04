package com.example.controllers;

import com.example.models.Category;
import com.example.models.Goods;
import com.example.models.GoodsAttributes;
import com.example.services.CategoryService;
import com.example.services.GoodsAttributesService;
import com.example.services.GoodsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private GoodsService goodsService;
    @Mock
    private GoodsAttributesService goodsAttributesService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private Model model;
    @Mock
    private MultipartFile imageFile;

    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        adminController = new AdminController(goodsService, goodsAttributesService, categoryService);
    }

    @Test
    void showAddProductFormTest() {
        List<Category> parentCategories = new ArrayList<>();
        when(categoryService.getParentCategories()).thenReturn(parentCategories);

        String viewName = adminController.showAddProductForm(model);

        assertEquals("goods/new", viewName);
        verify(model).addAttribute("goods", new Goods());
        verify(model).addAttribute("attributes", new GoodsAttributes());
        verify(model).addAttribute("parentCategories", parentCategories);
    }

    @Test
    void editProductTest() {
        int id = 1;
        Goods goods = new Goods();
        GoodsAttributes goodsAttributes = new GoodsAttributes();
        List<Category> categories = new ArrayList<>();

        when(goodsService.findOne(id)).thenReturn(goods);
        when(goodsAttributesService.getAttributesByGoodsId(id)).thenReturn(goodsAttributes);
        when(categoryService.getParentCategories()).thenReturn(categories);

        String viewName = adminController.editProduct(id, model);

        assertEquals("goods/edit", viewName);
        verify(model).addAttribute("goods", goods);
        verify(model).addAttribute("attributes", goodsAttributes);
        verify(model).addAttribute("categories", categories);
    }

    @Test
    void updateProductTest() throws IOException {
        int id = 1;
        int categoryId = 2;
        Goods goods = mock(Goods.class);
        MultipartFile imageFile = mock(MultipartFile.class);
        Category selectedCategory = new Category();

        when(categoryService.getCategoryById(categoryId)).thenReturn(selectedCategory);

        String viewName = adminController.updateProduct(id, goods, categoryId, imageFile);

        assertEquals("redirect:/goods/catalog", viewName);
        verify(goods).setCategory(selectedCategory);
        verify(goodsService).updateProduct(goods, imageFile, id);
    }

    @Test
    void addProductTest() throws IOException {
        Goods goods = new Goods();
        GoodsAttributes attributes = new GoodsAttributes();
        MultipartFile imageFile = mock(MultipartFile.class);
        int categoryId = 2;
        Category selectedCategory = new Category();

        when(categoryService.getCategoryById(categoryId)).thenReturn(selectedCategory);

        String viewName = adminController.addProduct(goods, attributes, imageFile, categoryId);

        assertEquals("redirect:/goods/catalog", viewName);
        verify(goodsService).saveProduct(goods, attributes, imageFile, selectedCategory);
    }
}