package com.ecommerce.ECommerceSite.services;

import com.ecommerce.ECommerceSite.config.MessageStrings;
import com.ecommerce.ECommerceSite.domain.dtos.AuthenticationToken;
import com.ecommerce.ECommerceSite.domain.dtos.User;
import com.ecommerce.ECommerceSite.domain.repositories.TokenRepository;
import com.ecommerce.ECommerceSite.exceptions.AuthenticationFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {
    @Autowired
    TokenRepository tokenRepository;

    // save the confirmation token
    public void saveConfirmationToken(AuthenticationToken authenticationToken){
        tokenRepository.save(authenticationToken);
    }

    // get token of the user
    public AuthenticationToken getToken(User user) {
        return tokenRepository.findTokenByUser(user);
    }

    // get user from the token
    public User getUser(String token){
        AuthenticationToken authenticationToken = tokenRepository.findTokenByToken(token);
        if (Objects.nonNull(authenticationToken)){
            if (Objects.nonNull(authenticationToken.getUser())){
                return authenticationToken.getUser();
            }
        }
        return null;
    }

    //check if the token is valid
    public void authenticate(String token) throws AuthenticationFailException {
        if (!Objects.nonNull(token)){
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
        }
        if (!Objects.nonNull(getUser(token))){
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_VALID);
        }
    }
}
