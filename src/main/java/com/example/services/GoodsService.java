package com.example.services;

import com.example.models.Goods;
import com.example.repositories.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GoodsService {
    private final GoodsRepository goodsRepository;

    @Autowired
    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    public Goods findOne(int id){
        Optional<Goods> foundGoods = goodsRepository.findById(id);
        return foundGoods.orElse(null);
    }
}
