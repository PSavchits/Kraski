package com.example.services;

import com.example.models.GoodsAttributes;
import com.example.repositories.GoodsAttributesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GoodsAttributesService {
    private final GoodsAttributesRepository goodsAttributesRepository;

    @Autowired
    public GoodsAttributesService(GoodsAttributesRepository goodsAttributesRepository) {
        this.goodsAttributesRepository = goodsAttributesRepository;
    }

    @Transactional
    public GoodsAttributes saveGoodsAttributes(GoodsAttributes attributes) {
        return goodsAttributesRepository.save(attributes);
    }

    public GoodsAttributes getAttributesByGoodsId(int id) {
        return goodsAttributesRepository.findById(id);
    }
}
