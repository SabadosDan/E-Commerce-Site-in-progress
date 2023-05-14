package com.ecommerce.ECommerceSite.controllers;

import com.ecommerce.ECommerceSite.controllers.responses.BaseResponse;
import com.ecommerce.ECommerceSite.controllers.responses.CategoryResponse;
import com.ecommerce.ECommerceSite.controllers.responses.ErrorResponse;
import com.ecommerce.ECommerceSite.controllers.responses.ListCategoriesResponse;
import com.ecommerce.ECommerceSite.exceptions.DuplicateEntityException;
import com.ecommerce.ECommerceSite.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "")
    public ResponseEntity<BaseResponse> createNewCategory(@RequestBody CategoryResponse categoryResponse){
        try{
            return new ResponseEntity<>(categoryService.createCategory(categoryResponse), HttpStatus.OK);
        } catch (DuplicateEntityException e){
            return new ResponseEntity<>(new ErrorResponse(
                    e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/all_categories")
    public ResponseEntity<BaseResponse> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }
}
