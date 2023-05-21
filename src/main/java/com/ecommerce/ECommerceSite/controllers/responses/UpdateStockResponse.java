package com.ecommerce.ECommerceSite.controllers.responses;

public class UpdateStockResponse extends BaseResponse{
    private String UpdateStockMessage;

    public UpdateStockResponse(){
        this.UpdateStockMessage = "";
    }
    public UpdateStockResponse(String updateStockMessage) {
        this.UpdateStockMessage = updateStockMessage;
    }

    public String getUpdateStockMessage() {
        return UpdateStockMessage;
    }

    public void setUpdateStockMessage(String updateStockMessage) {
        UpdateStockMessage = updateStockMessage;
    }
}
