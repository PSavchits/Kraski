package com.example.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsTest {

    private Goods goods;
    private int id;
    private String productName;
    private BigDecimal price;
    private String description;
    private int availableQuantity;
    private String imageFilename;
    private Category category;
    private Date dateAdded;
    private Date dateUpdated;
    private GoodsAttributes attributes;

    @BeforeEach
    void setUp() {
        id = 1;
        productName = "Laptop";
        price = new BigDecimal("999.99");
        description = "Powerful laptop for gaming and productivity";
        availableQuantity = 10;
        imageFilename = "laptop.jpg";
        category = new Category();
        dateAdded = new Date();
        dateUpdated = new Date();
        attributes = new GoodsAttributes();
    }

    @Test
    void goodsConstructorTest() {
        goods = new Goods(id, productName, price, description, availableQuantity,
                imageFilename, category, dateAdded, dateUpdated, attributes);

        assertEquals(id, goods.getId());
        assertEquals(productName, goods.getProductName());
        assertEquals(price, goods.getPrice());
        assertEquals(description, goods.getDescription());
        assertEquals(availableQuantity, goods.getAvailableQuantity());
        assertEquals(imageFilename, goods.getImageFilename());
        assertEquals(category, goods.getCategory());
        assertEquals(dateAdded, goods.getDateAdded());
        assertEquals(dateUpdated, goods.getDateUpdated());
        assertEquals(attributes, goods.getAttributes());
    }

    @Test
    void settersAndGettersTest() {
        goods = new Goods();

        goods.setId(id);
        goods.setProductName(productName);
        goods.setPrice(price);
        goods.setDescription(description);
        goods.setAvailableQuantity(availableQuantity);
        goods.setImageFilename(imageFilename);
        goods.setCategory(category);
        goods.setDateAdded(dateAdded);
        goods.setDateUpdated(dateUpdated);
        goods.setAttributes(attributes);

        assertEquals(id, goods.getId());
        assertEquals(productName, goods.getProductName());
        assertEquals(price, goods.getPrice());
        assertEquals(description, goods.getDescription());
        assertEquals(availableQuantity, goods.getAvailableQuantity());
        assertEquals(imageFilename, goods.getImageFilename());
        assertEquals(category, goods.getCategory());
        assertEquals(dateAdded, goods.getDateAdded());
        assertEquals(dateUpdated, goods.getDateUpdated());
        assertEquals(attributes, goods.getAttributes());
    }
}