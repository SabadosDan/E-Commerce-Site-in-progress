package com.ecommerce.ECommerceSite.services;

import com.ecommerce.ECommerceSite.domain.repositories.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;

    //UserRepo ?? ProductRepo ??

    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }
}
