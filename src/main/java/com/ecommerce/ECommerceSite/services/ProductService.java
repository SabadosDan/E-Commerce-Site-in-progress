package com.ecommerce.ECommerceSite.services;

import com.ecommerce.ECommerceSite.controllers.responses.ProductResponse;
import com.ecommerce.ECommerceSite.domain.dtos.Category;
import com.ecommerce.ECommerceSite.domain.dtos.Product;
import com.ecommerce.ECommerceSite.domain.repositories.CategoryRepository;
import com.ecommerce.ECommerceSite.domain.repositories.ProductRepository;
import com.ecommerce.ECommerceSite.exceptions.DuplicateEntityException;
import com.ecommerce.ECommerceSite.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.NotActiveException;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponse createProduct(ProductResponse productResponse) throws DuplicateEntityException {
        Optional<Product> product = productRepository.findByName(productResponse.getName());

        if (product.isPresent()) {
            throw new DuplicateEntityException("Duplicate product with name " + productResponse.getName());
        }

        Category category = productResponse.getCategory();
        if (!categoryRepository.existsById(category.getId())){
            throw new NotFoundException("Not found category with name " + category.getCategoryName());
        }

        Product productEntity = new Product(productResponse);
        productEntity = productRepository.save(productEntity);
        return new ProductResponse(productEntity);
    }
}
