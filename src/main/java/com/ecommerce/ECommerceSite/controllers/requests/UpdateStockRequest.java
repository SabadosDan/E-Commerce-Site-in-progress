package com.ecommerce.ECommerceSite.controllers.requests;

public class UpdateStockRequest {
    private Long productId;
    private Integer quantityToAddInStock;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantityToAddInStock() {
        return quantityToAddInStock;
    }

    public void setQuantityToAddInStock(Integer quantityToAddInStock) {
        this.quantityToAddInStock = quantityToAddInStock;
    }
}
