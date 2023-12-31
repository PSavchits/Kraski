package com.example.repositories;

import com.example.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByParentCategoryId(long parentCategoryId);

    List<Category> findByParentCategoryIdNotNull();
}
