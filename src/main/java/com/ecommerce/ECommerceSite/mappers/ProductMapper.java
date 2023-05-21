package com.ecommerce.ECommerceSite.mappers;

import com.ecommerce.ECommerceSite.controllers.responses.ProductResponse;
import com.ecommerce.ECommerceSite.domain.dtos.Category;
import com.ecommerce.ECommerceSite.domain.dtos.Product;
import com.ecommerce.ECommerceSite.domain.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    final private CategoryRepository categoryRepository;

    public ProductMapper(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Product mapToProduct(ProductResponse productResponse){
        Category category = categoryRepository.findByCategoryName(productResponse.getCategoryName());
        return new Product(productResponse.getName(), productResponse.getDescription(),
                            productResponse.getPrice(), category, productResponse.getImageUrl(),
                            productResponse.getQuantityInStock());
    }
}
