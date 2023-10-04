package com.example.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class GoodsAttributesTest {

    private GoodsAttributes attributes;
    private int id;
    private String color;
    private String country;
    private BigDecimal length;
    private String material;

    @BeforeEach
    void setUp() {
        id = 1;
        color = "Red";
        country = "USA";
        length = new BigDecimal("10.5");
        material = "Cotton";
    }

    @Test
    void goodsAttributesConstructorTest() {
        attributes = new GoodsAttributes(id, color, country, length, material);

        assertEquals(id, attributes.getId());
        assertEquals(color, attributes.getColor());
        assertEquals(country, attributes.getCountry());
        assertEquals(length, attributes.getLength());
        assertEquals(material, attributes.getMaterial());
    }

    @Test
    void settersAndGettersTest() {
        attributes = new GoodsAttributes();

        attributes.setId(id);
        attributes.setColor(color);
        attributes.setCountry(country);
        attributes.setLength(length);
        attributes.setMaterial(material);

        assertEquals(id, attributes.getId());
        assertEquals(color, attributes.getColor());
        assertEquals(country, attributes.getCountry());
        assertEquals(length, attributes.getLength());
        assertEquals(material, attributes.getMaterial());
    }
}
