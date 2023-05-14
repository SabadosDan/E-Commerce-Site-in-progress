package com.ecommerce.ECommerceSite.services;

import com.ecommerce.ECommerceSite.controllers.CategoryController;
import com.ecommerce.ECommerceSite.controllers.responses.CategoryResponse;
import com.ecommerce.ECommerceSite.controllers.responses.ListCategoriesResponse;
import com.ecommerce.ECommerceSite.domain.dtos.Category;
import com.ecommerce.ECommerceSite.domain.repositories.CategoryRepository;
import com.ecommerce.ECommerceSite.exceptions.DuplicateEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse createCategory(CategoryResponse categoryResponse) throws DuplicateEntityException {
        Optional<Category> category = categoryRepository.findByCategoryName(categoryResponse.getCategoryName());

        if (category.isPresent()) {
            throw new DuplicateEntityException("Duplicate category with name " + categoryResponse.getCategoryName());
        }
        //Creating a new "Category" object with the data from "categoryResponse"
        Category categoryEntity = new Category(categoryResponse);
        //Saving the "Category" object to the repository using "save"
        //The result of the addition operation is stored in "categoryEntity"
        categoryEntity = categoryRepository.save(categoryEntity);
        //Returning a new "CategoryResponse" object created with the data from the saved "Category" object
        return new CategoryResponse(categoryEntity);
    }

    public ListCategoriesResponse getAllCategories(){
        //creating a CategoryResponse list from repository
        List<Category> categoryList = this.categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category: categoryList){
            categoryResponses.add(new CategoryResponse(category));
        }
        return new ListCategoriesResponse(categoryResponses);
    }
}
