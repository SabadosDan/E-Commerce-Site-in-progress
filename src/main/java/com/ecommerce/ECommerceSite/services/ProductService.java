package com.ecommerce.ECommerceSite.services;

import com.ecommerce.ECommerceSite.controllers.requests.UpdateStockRequest;
import com.ecommerce.ECommerceSite.controllers.responses.ListProductsResponse;
import com.ecommerce.ECommerceSite.controllers.responses.ProductResponse;
import com.ecommerce.ECommerceSite.controllers.responses.UpdateStockResponse;
import com.ecommerce.ECommerceSite.domain.dtos.Product;
import com.ecommerce.ECommerceSite.domain.repositories.CategoryRepository;
import com.ecommerce.ECommerceSite.domain.repositories.ProductRepository;
import com.ecommerce.ECommerceSite.exceptions.DuplicateEntityException;
import com.ecommerce.ECommerceSite.exceptions.NotFoundException;
import com.ecommerce.ECommerceSite.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public ProductResponse createProduct(ProductResponse productResponse) throws DuplicateEntityException {
        Optional<Product> product = productRepository.findByName(productResponse.getName());

        if (product.isPresent()) {
            throw new DuplicateEntityException("Duplicate product with name " + productResponse.getName() +
                    ". If you have more products with the same name, you should update the stock instead. ");
        }

        String categoryName = productResponse.getCategoryName();
        if (categoryRepository.findByCategoryName(categoryName) == null){
            throw new NotFoundException("Not found category with name " + categoryName);
        }

        Product productEntity = productMapper.mapToProduct(productResponse);
        productEntity = productRepository.save(productEntity);
        return new ProductResponse(productEntity);
    }

    public ListProductsResponse getAllProducts(){
        List<Product> productList = this.productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product: productList){
            productResponses.add(new ProductResponse(product));
        }
        return new ListProductsResponse(productResponses);
    }

    public UpdateStockResponse UpdateStock(UpdateStockRequest updateStockRequest) throws NotFoundException{
        Optional<Product> optionalProduct = productRepository.findById(updateStockRequest.getProductId());
        if (optionalProduct.isEmpty()){
            throw new NotFoundException("Product not found!");
        }

        Product product = optionalProduct.get();
        Integer oldQuantity = product.getQuantityInStock();
        Integer updatedQuantity = oldQuantity + updateStockRequest.getQuantityToAddInStock();

        product.setQuantityInStock(updatedQuantity);
        if (updatedQuantity == 0){
            product.setInStock(Boolean.FALSE);
        }
        productRepository.save(product);

        return new UpdateStockResponse("Stock added succesfully! The new stock of "
                + product.getName() + " is " + updatedQuantity + ".");
    }
}
