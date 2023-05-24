package com.ecommerce.ECommerceSite.controllers;

import com.ecommerce.ECommerceSite.controllers.responses.BaseResponse;
import com.ecommerce.ECommerceSite.controllers.responses.ErrorResponse;
import com.ecommerce.ECommerceSite.controllers.responses.UserResponse;
import com.ecommerce.ECommerceSite.exceptions.DuplicateEntityException;
import com.ecommerce.ECommerceSite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}


