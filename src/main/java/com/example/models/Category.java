package com.example.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    // Если хотим добавить подкатегории
    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
    private List<Category> subcategories;

    // Если хотим добавить товары в категорию
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Goods> goodsList;
}