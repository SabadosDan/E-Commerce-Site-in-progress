package com.ecommerce.ECommerceSite.services;

import com.ecommerce.ECommerceSite.config.MessageStrings;
import com.ecommerce.ECommerceSite.controllers.responses.LoginResponse;
import com.ecommerce.ECommerceSite.controllers.responses.RegistrationResponse;
import com.ecommerce.ECommerceSite.controllers.responses.UserResponse;
import com.ecommerce.ECommerceSite.domain.dtos.*;
import com.ecommerce.ECommerceSite.domain.repositories.CartRepository;
import com.ecommerce.ECommerceSite.domain.repositories.UserRepository;
import com.ecommerce.ECommerceSite.exceptions.AuthenticationFailException;
import com.ecommerce.ECommerceSite.exceptions.DuplicateEntityException;
import com.ecommerce.ECommerceSite.exceptions.NotFoundException;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final AuthenticationService authenticationService;

    @Autowired
    public UserService(UserRepository userRepository, AuthenticationService authenticationService){
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
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

    public RegistrationResponse register (Registration registration) throws DuplicateEntityException, NoSuchAlgorithmException {
        // Check to see if the current username has already been registered.
        Optional<User> user = userRepository.findByUsername(registration.getUsername());
        if (user.isPresent()){
            throw new DuplicateEntityException("User already exists.");
        }

        // encrypt the password
        String encryptedPassword = registration.getPassword();
        try{
            encryptedPassword = hashPassword(registration.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new NoSuchAlgorithmException("hashing password failed {}" + e.getMessage());
        }

        User newUser = new User(registration.getUsername(), registration.getEmail(), registration.getPassword());
        try {
            Cart cart = newUser.getCart();
            cart.setUser(newUser);

            userRepository.save(newUser);
            // success in creating
            // generate token for user
            final AuthenticationToken authenticationToken = new AuthenticationToken(newUser);
            // save token in database
            authenticationService.saveConfirmationToken(authenticationToken);
            return new RegistrationResponse("success", "user created successfully");
        } catch (Exception e) {
            // handle registration error
            throw new DuplicateEntityException(e.getMessage());
        }

    }

    String hashPassword(String password) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }

    public LoginResponse login(Login login) throws AuthenticationFailException, NotFoundException{
        // find user by username
        Optional<User> optionalUser = userRepository.findByUsername(login.getUsername());
        if (optionalUser.isEmpty()){
            throw new AuthenticationFailException("user not present");
        }
        User user = optionalUser.get();
        try {
            // check if password is right
            if (!user.getPassword().equals(hashPassword(login.getPassword()))){
                //passwords do not match
                throw new AuthenticationFailException(MessageStrings.WRONG_PASSWORD);
            }
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            throw new NotFoundException("hashing password failed {}" + e.getMessage());
        }

        AuthenticationToken token = authenticationService.getToken(user);

        if(!Objects.nonNull(token)) {
            //token not present
            throw new NotFoundException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
        }

        return new LoginResponse("success", token.getToken());
    }
}
