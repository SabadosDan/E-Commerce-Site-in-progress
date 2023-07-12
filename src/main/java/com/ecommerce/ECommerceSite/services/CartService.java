package com.ecommerce.ECommerceSite.services;

import com.ecommerce.ECommerceSite.controllers.requests.AddToCartRequest;
import com.ecommerce.ECommerceSite.controllers.responses.AddToCartResponse;
import com.ecommerce.ECommerceSite.domain.dtos.Cart;
import com.ecommerce.ECommerceSite.domain.dtos.CartItem;
import com.ecommerce.ECommerceSite.domain.dtos.Product;
import com.ecommerce.ECommerceSite.domain.dtos.User;
import com.ecommerce.ECommerceSite.domain.repositories.CartRepository;
import com.ecommerce.ECommerceSite.domain.repositories.ProductRepository;
import com.ecommerce.ECommerceSite.domain.repositories.UserRepository;
import com.ecommerce.ECommerceSite.exceptions.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    //UserRepo ?? ProductRepo ??

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    // Adding a new CartItem in cart items list from the cart
    public void addCartItem(User user, CartItem cartItem){
        Cart cart = user.getCart();
        cart.getCartItems().add(cartItem);

        // Update the total price of the cart
        updateCartTotalPrice(cart);

        // Save the updated cart to the database
        cartRepository.save(cart);
    }

    // Updating the total price of the cart
    private void updateCartTotalPrice(Cart cart){
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItem cartItem : cart.getCartItems()){
            totalPrice = totalPrice.add(cartItem.getPrice());
        }
    }

    public AddToCartResponse addToCart(AddToCartRequest addToCartRequest) throws NotFoundException {
        Long productId = addToCartRequest.getProductId();
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()){
            throw new NotFoundException("Product not found!");
        }
        Product product = optionalProduct.get();

        Integer quantity = addToCartRequest.getQuantity();
        BigDecimal price = product.getPrice().multiply(BigDecimal.valueOf(quantity));

        // Create a new CartItem object using the retrieved parameters
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(price);

        User user = getCurrentUser();
        addCartItem(user, cartItem);

        userRepository.save(user);

        return new AddToCartResponse(product.getName() + " added succesfully to the cart! ");
    }

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User){
            return (User) authentication.getPrincipal();
        }
        return null;
    }
}
