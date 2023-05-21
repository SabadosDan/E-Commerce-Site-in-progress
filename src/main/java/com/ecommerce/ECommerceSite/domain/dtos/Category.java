package com.ecommerce.ECommerceSite.domain.dtos;

import com.ecommerce.ECommerceSite.controllers.responses.CategoryResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@Getter @Setter
public class Category extends BaseEntity{

    @Column(name = "category_name")
    private String categoryName;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    public Category(CategoryResponse categoryResponse){
        this.categoryName = categoryResponse.getCategoryName();
        this.description = categoryResponse.getDescription();
        this.imageUrl = categoryResponse.getImageUrl();
    }
    public Category() {}
}
