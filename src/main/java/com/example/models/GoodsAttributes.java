package com.example.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "goods_attributes")
public class GoodsAttributes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attributes_id")
    private int id;

    @Column(name = "attribute_color")
    private String color;

    @Column(name = "attribute_country")
    private String country;

    @Column(name = "attribute_length")
    private BigDecimal length;

    @Column(name = "attribute_material")
    private String material;
}
