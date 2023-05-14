package com.ecommerce.ECommerceSite.controllers.responses;

import com.ecommerce.ECommerceSite.domain.dtos.Category;
import com.ecommerce.ECommerceSite.domain.dtos.Product;

import java.math.BigDecimal;

public class ProductResponse extends BaseResponse{
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category; //TODO schimba in category name -> schimbari in constructeri -> schimbari in createProduct <> Nevoie de findByName pt caterogy
    private String imageUrl;
    private Integer quantityInStock;

    public ProductResponse(){};

    public ProductResponse(Long id, String name, String description, BigDecimal price, Category category, String imageUrl, Integer quantityInStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
        this.quantityInStock = quantityInStock;
    }

    public ProductResponse(Product product) {
        super(); //TODO
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
