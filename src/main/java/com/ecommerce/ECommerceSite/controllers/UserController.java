package com.ecommerce.ECommerceSite.controllers;

import com.ecommerce.ECommerceSite.controllers.responses.*;
import com.ecommerce.ECommerceSite.domain.dtos.Login;
import com.ecommerce.ECommerceSite.domain.dtos.Registration;
import com.ecommerce.ECommerceSite.exceptions.AuthenticationFailException;
import com.ecommerce.ECommerceSite.exceptions.DuplicateEntityException;
import com.ecommerce.ECommerceSite.exceptions.NotFoundException;
import com.ecommerce.ECommerceSite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "")
    public ResponseEntity<BaseResponse> createNewUser(@RequestBody UserResponse userResponse){
        try{
            return new ResponseEntity<>(userService.createNewUser(userResponse), HttpStatus.OK);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public RegistrationResponse register(@RequestBody Registration registration) throws DuplicateEntityException, NoSuchAlgorithmException {
        return userService.register(registration);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody Login login) throws NotFoundException, AuthenticationFailException {
        return userService.login(login);
    }
}


