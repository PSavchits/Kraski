package com.example.repositories;

import com.example.models.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
}
