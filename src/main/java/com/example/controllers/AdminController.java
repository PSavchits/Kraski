package com.example.controllers;

import com.example.models.Goods;

import com.example.services.GoodsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private final GoodsService goodsService;

    @Autowired
    public AdminController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }



    @GetMapping("/goods/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("goods", new Goods());
        return "goods/new";
    }

    @PostMapping("/goods")
    public String addProduct(@ModelAttribute Goods goods,
                             @RequestParam("image") MultipartFile imageFile) throws IOException {
        goodsService.saveProduct(goods, imageFile);
        return "redirect:/goods/catalog";
    }
}
