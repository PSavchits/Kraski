package com.example.controllers;

import com.example.models.Category;
import com.example.models.Goods;
import com.example.models.GoodsAttributes;
import com.example.services.CategoryService;
import com.example.services.GoodsAttributesService;
import com.example.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsService goodsService;

    private final CategoryService categoryService;

    private final GoodsAttributesService goodsAttributesService;

    @Autowired
    public GoodsController(GoodsService goodsService, CategoryService categoryService, GoodsAttributesService goodsAttributesService) {
        this.goodsService = goodsService;
        this.categoryService = categoryService;
        this.goodsAttributesService = goodsAttributesService;
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        List<Goods> allGoods = goodsService.findAll();

        model.addAttribute("allGoods", allGoods);

        return "goods/catalog";
    }

    @GetMapping("/catalog/{id}")
    public String category(Model model, @PathVariable("id") int id) {
        Category category = categoryService.getCategoryById(id);
        List<Category> subcategories = categoryService.getSubcategoriesByParentCategoryId(id);

        model.addAttribute("category", category);
        model.addAttribute("subcategories", subcategories);

        return "goods/category";
    }

    @GetMapping("/category/{id}")
    public String categoryGoods(Model model, @PathVariable("id") Long id) {
        List<Goods> allGoods = goodsService.findByCategoryId(id);

        model.addAttribute("allGoods", allGoods);

        return "goods/catalog";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Goods goods = goodsService.findOne(id);

        model.addAttribute("goods", goods);
        return "goods/show";
    }
    @GetMapping()
    public String main() {
        return "/main";
    }
}
