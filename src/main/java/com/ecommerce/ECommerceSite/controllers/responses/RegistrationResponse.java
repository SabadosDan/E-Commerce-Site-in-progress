package com.ecommerce.ECommerceSite.controllers.responses;

public class RegistrationResponse extends BaseResponse{
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RegistrationResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
