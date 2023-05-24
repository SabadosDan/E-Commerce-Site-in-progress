package com.ecommerce.ECommerceSite.domain.repositories;

import com.ecommerce.ECommerceSite.domain.dtos.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
