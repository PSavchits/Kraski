package com.example.repositories;

import com.example.models.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GoodsRepository extends JpaRepository<Goods, Integer> {
}
