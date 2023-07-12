package com.ecommerce.ECommerceSite.domain.repositories;

import com.ecommerce.ECommerceSite.domain.dtos.AuthenticationToken;
import com.ecommerce.ECommerceSite.domain.dtos.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository  extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findTokenByUser(User user);
    AuthenticationToken findTokenByToken(String Token);
}
