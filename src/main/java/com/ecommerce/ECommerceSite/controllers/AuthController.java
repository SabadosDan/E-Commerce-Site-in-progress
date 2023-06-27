package com.ecommerce.ECommerceSite.controllers;

import com.ecommerce.ECommerceSite.domain.dtos.User;
import com.ecommerce.ECommerceSite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @PostMapping("/login") // Perform authentication logic and handle login success or failure
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Authentication authentication) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // Redirect the user to the appropriate page
            return "redirect:/dashboard";
        } catch (AuthenticationException e){
            return "redirect:/login?error";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm(){
        return "register";
    }

    @PostMapping("/register") // Perform registration logic, create user account and handle registration success
    public String register(@RequestParam("username") String username, @RequestParam("email")String email,
                           @RequestParam("password") String password){
        // Redirect the user to the appropriate page
        return "redirect:/dashboard";
    }
}
