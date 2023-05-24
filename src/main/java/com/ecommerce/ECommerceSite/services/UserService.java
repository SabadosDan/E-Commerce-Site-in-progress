package com.ecommerce.ECommerceSite.services;

import com.ecommerce.ECommerceSite.controllers.responses.UserResponse;
import com.ecommerce.ECommerceSite.domain.dtos.Cart;
import com.ecommerce.ECommerceSite.domain.dtos.User;
import com.ecommerce.ECommerceSite.domain.repositories.CartRepository;
import com.ecommerce.ECommerceSite.domain.repositories.UserRepository;
import com.ecommerce.ECommerceSite.exceptions.DuplicateEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponse createNewUser(UserResponse userResponse) throws DuplicateEntityException {
        Optional<User> userOptional = userRepository.findByUsername(userResponse.getUsername());
        if (userOptional.isPresent()){
            throw new DuplicateEntityException("Username " + userResponse.getUsername() +" already taken!");
        }

        User user = new User(userResponse);

        Cart cart = user.getCart();
        cart.setUser(user);
        //user.setCart(cart);

        user = userRepository.save(user);
        return new UserResponse(user);
    }
}
