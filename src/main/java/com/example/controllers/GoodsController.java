package com.example.controllers;

import com.example.models.Goods;
import com.example.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

        @GetMapping("/catalog")
        public String catalog(Model model) {
            List<Goods> allGoods = goodsService.findAll();
            List<String> imagePaths = new ArrayList<>();

            for (Goods goods : allGoods) {
                String filename = goods.getImageFilename();
                String imagePath = "/static/images/" + filename;
                imagePaths.add(imagePath);
            }

            model.addAttribute("allGoods", allGoods);
            model.addAttribute("imagePaths", imagePaths);

            return "goods/catalog";
        }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Goods goods = goodsService.findOne(id);

        model.addAttribute("goods", goods);

        return "goods/show";
    }
}
