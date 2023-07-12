package com.ecommerce.ECommerceSite.controllers.responses;

public class LoginResponse extends BaseResponse{
    private String status;
    private String token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResponse(String status, String token) {
        this.status = status;
        this.token = token;
    }
}
