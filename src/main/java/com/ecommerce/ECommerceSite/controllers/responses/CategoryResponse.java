package com.ecommerce.ECommerceSite.controllers.responses;

import com.ecommerce.ECommerceSite.domain.dtos.Category;

public class CategoryResponse extends BaseResponse{
    private Long id;
    private String categoryName;
    private String description;
    private String imageUrl;

    public CategoryResponse(Long id, String categoryName, String description, String imageUrl) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public CategoryResponse(){
    }

    public CategoryResponse(Category category){
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
        this.description = category.getDescription();
        this.imageUrl = category.getImageUrl();
    }

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
