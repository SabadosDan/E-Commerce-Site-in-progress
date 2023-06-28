package com.ecommerce.ECommerceSite.controllers;

import com.ecommerce.ECommerceSite.domain.dtos.User;
import com.ecommerce.ECommerceSite.domain.repositories.UserRepository;
import com.ecommerce.ECommerceSite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @PostMapping("/login") // Perform authentication logic and handle login success or failure
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            Optional<User> optionalUser = userRepository.findByUsername(username);
            User user = optionalUser.get();
            if (user != null) {
                if (passwordEncoder.matches(password, user.getPassword())) {
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                    return "redirect:/h2-console";
                }
            }
            // Redirect the user to the appropriate page
        } catch (AuthenticationException e){
            return "redirect:/login?error";
        }
        return "redirect:/h2-console";
    }

    @GetMapping("/register")
    public String showRegistrationForm(){
        return "register";
    }

    @PostMapping("/register") // Perform registration logic, create user account and handle registration success
    public String register(@RequestParam("username") String username, @RequestParam("email")String email,
                           @RequestParam("password") String password){
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        //newUser.setPassword(password);

        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encodedPassword);

        userRepository.save(newUser);
        // Redirect the user to the appropriate page
        return "redirect:/h2-console";
    }
}
