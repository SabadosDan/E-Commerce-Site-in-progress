package com.ecommerce.ECommerceSite.domain.dtos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @Column(name = "cart_items")
    private List<CartItem> cartItems = new ArrayList<>();

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    public Cart() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Cart(User user, List<CartItem> cartItems, BigDecimal totalPrice) {
        this.user = user;
        this.totalPrice = totalPrice;
    }
}
