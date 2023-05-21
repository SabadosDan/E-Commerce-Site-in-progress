package com.ecommerce.ECommerceSite.domain.repositories;

import com.ecommerce.ECommerceSite.domain.dtos.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(String categoryName);
}
