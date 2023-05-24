package com.ecommerce.ECommerceSite.domain.dtos;

import com.ecommerce.ECommerceSite.controllers.responses.CategoryResponse;
import com.ecommerce.ECommerceSite.controllers.responses.ProductResponse;
import com.ecommerce.ECommerceSite.domain.repositories.ProductRepository;
import com.sun.xml.bind.v2.TODO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor
public class Product extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category ;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "quantity")
    private Integer quantityInStock;

    @Column(name = "in_Stock")
    private Boolean inStock;

    public Product(String name, String description, BigDecimal price,
                   Category category, String imageUrl, Integer quantityInStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
        this.quantityInStock = quantityInStock;
        this.inStock = this.quantityInStock > 0;
    }
}
