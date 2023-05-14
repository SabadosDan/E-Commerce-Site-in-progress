package com.ecommerce.ECommerceSite.controllers.responses;

import java.util.List;

public class ListCategoriesResponse extends BaseResponse{
    private List<CategoryResponse> categoryResponses;

    public ListCategoriesResponse(List<CategoryResponse> categoryResponses) {
        this.categoryResponses = categoryResponses;
    }

    public List<CategoryResponse> getCategoryResponses() {
        return categoryResponses;
    }

    public void setCategoryResponses(List<CategoryResponse> categoryResponses) {
        this.categoryResponses = categoryResponses;
    }
}
