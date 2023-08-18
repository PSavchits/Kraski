package com.example.controllers;

import com.example.models.Goods;

import com.example.models.GoodsAttributes;
import com.example.services.GoodsAttributesService;
import com.example.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private final GoodsService goodsService;

    private final GoodsAttributesService goodsAttributesService;

    @Autowired
    public AdminController(GoodsService goodsService, GoodsAttributesService goodsAttributesService) {
        this.goodsService = goodsService;
        this.goodsAttributesService = goodsAttributesService;
    }



    @GetMapping("/goods/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("goods", new Goods());
        model.addAttribute("attributes", new GoodsAttributes());
        return "goods/new";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        Goods goods = goodsService.findOne(id);
        GoodsAttributes goodsAttributes = goodsAttributesService.getAttributesByGoodsId(id);
        model.addAttribute("goods", goods);
        model.addAttribute("attributes", goodsAttributes);
        return "goods/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") int id,
                                @ModelAttribute("goods") Goods goods,
                                @RequestParam("image") MultipartFile imageFile) throws IOException {
        System.out.println("Before setting attributes in GoodsAttributes:");
        System.out.println("Color: " + goods.getAttributes().getColor());
        System.out.println("Country: " + goods.getAttributes().getCountry());
        // ... другие атрибуты ...

        goodsService.updateProduct(goods, imageFile, id);

        System.out.println("After setting attributes in GoodsAttributes:");
        System.out.println("Color: " + goods.getAttributes().getColor());
        System.out.println("Country: " + goods.getAttributes().getCountry());
        return "redirect:/goods/catalog";
    }

    @PostMapping("/goods/add")
    public String addProduct(@ModelAttribute Goods goods,
                             @ModelAttribute GoodsAttributes attributes,
                             @RequestParam("image") MultipartFile imageFile) throws IOException {
        goodsService.saveProduct(goods, attributes, imageFile);

        return "redirect:/goods/catalog";
    }
}
