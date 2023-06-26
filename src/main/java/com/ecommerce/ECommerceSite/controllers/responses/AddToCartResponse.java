package com.ecommerce.ECommerceSite.controllers.responses;

public class AddToCartResponse extends BaseResponse{
    private String AddToCartMessage;

    public AddToCartResponse(String addToCartMessage) {
        AddToCartMessage = addToCartMessage;
    }

    public String getAddToCartMessage() {
        return AddToCartMessage;
    }

    public void setAddToCartMessage(String addToCartMessage) {
        AddToCartMessage = addToCartMessage;
    }
}
