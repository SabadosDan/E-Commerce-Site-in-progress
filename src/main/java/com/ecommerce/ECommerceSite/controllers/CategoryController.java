package com.ecommerce.ECommerceSite.controllers;

import com.ecommerce.ECommerceSite.controllers.responses.BaseResponse;
import com.ecommerce.ECommerceSite.controllers.responses.CategoryResponse;
import com.ecommerce.ECommerceSite.controllers.responses.ErrorResponse;
import com.ecommerce.ECommerceSite.exceptions.DuplicateEntityException;
import com.ecommerce.ECommerceSite.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/category")
    public ResponseEntity<BaseResponse> createNewCategory(@RequestBody CategoryResponse categoryResponse){
        try{
            return new ResponseEntity<>(categoryService.createCategory(categoryResponse), HttpStatus.OK);
        } catch (DuplicateEntityException e){
            return new ResponseEntity<>(new ErrorResponse(
                    e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
