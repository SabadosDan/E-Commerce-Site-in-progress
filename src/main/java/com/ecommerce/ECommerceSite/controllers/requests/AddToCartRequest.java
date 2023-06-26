package com.ecommerce.ECommerceSite.controllers.requests;

import com.ecommerce.ECommerceSite.domain.dtos.CartItem;

public class AddToCartRequest {
    private Long productId;
    private Integer quantity;

    public AddToCartRequest(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
