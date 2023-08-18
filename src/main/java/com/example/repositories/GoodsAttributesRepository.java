package com.example.repositories;

import com.example.models.GoodsAttributes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsAttributesRepository extends JpaRepository<GoodsAttributes, Integer> {
    GoodsAttributes findById(int id);
}
