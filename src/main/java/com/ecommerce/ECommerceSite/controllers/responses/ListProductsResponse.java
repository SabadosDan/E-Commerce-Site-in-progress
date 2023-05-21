package com.ecommerce.ECommerceSite.controllers.responses;

import java.util.List;

public class ListProductsResponse extends BaseResponse{
    private List<ProductResponse> productResponses;

    public ListProductsResponse(List<ProductResponse> productResponses){
        this.productResponses = productResponses;
    }

    public List<ProductResponse> getProductResponses() {
        return productResponses;
    }

    public void setProductResponses(List<ProductResponse> productResponses) {
        this.productResponses = productResponses;
    }
}
