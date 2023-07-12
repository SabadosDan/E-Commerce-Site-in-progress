package com.ecommerce.ECommerceSite.exceptions;

public class AuthenticationFailException extends Exception{
    public AuthenticationFailException(String msg) {
        super(msg);
    }
}
