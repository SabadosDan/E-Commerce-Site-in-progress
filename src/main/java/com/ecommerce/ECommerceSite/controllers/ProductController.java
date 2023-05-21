package com.ecommerce.ECommerceSite.controllers;

import com.ecommerce.ECommerceSite.controllers.requests.UpdateStockRequest;
import com.ecommerce.ECommerceSite.controllers.responses.BaseResponse;
import com.ecommerce.ECommerceSite.controllers.responses.ErrorResponse;
import com.ecommerce.ECommerceSite.controllers.responses.ProductResponse;
import com.ecommerce.ECommerceSite.exceptions.DuplicateEntityException;
import com.ecommerce.ECommerceSite.exceptions.NotFoundException;
import com.ecommerce.ECommerceSite.services.CategoryService;
import com.ecommerce.ECommerceSite.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping(value = "")
    public ResponseEntity<BaseResponse> createNewProduct(@RequestBody ProductResponse productResponse){
        try{
            return new ResponseEntity<>(productService.createProduct(productResponse), HttpStatus.OK);
        } catch (DuplicateEntityException | NotFoundException e){
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/all_products")
    public ResponseEntity<BaseResponse> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PutMapping(value = "/update_stock")
    public ResponseEntity<BaseResponse> updateStock(@RequestBody UpdateStockRequest updateStockRequest){
        try{
            return new ResponseEntity<>(productService.UpdateStock(updateStockRequest), HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
