package com.ecommerce.ECommerceSite.domain.dtos;

import com.ecommerce.ECommerceSite.controllers.responses.UserResponse;
import com.fasterxml.jackson.databind.ser.Serializers;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public User(UserResponse userResponse){
        this.username = userResponse.getUsername();
        this.email = userResponse.getEmail();
        this.password = userResponse.getPassword();
        this.cart = new Cart();
    }
}
