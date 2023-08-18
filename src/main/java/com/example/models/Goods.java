package com.example.models;

import com.example.models.Category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    public int id;

    @Column(name = "product_name", nullable = false)
    public String productName;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "available_quantity", nullable = false)
    private int availableQuantity;

    @Column(name = "image_filename")
    private String imageFilename;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @Column(name = "date_added")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateAdded;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_updated")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateUpdated;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "good_attribute", referencedColumnName = "attributes_id")
    private GoodsAttributes attributes;

}
