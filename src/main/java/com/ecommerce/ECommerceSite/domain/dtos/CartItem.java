package com.ecommerce.ECommerceSite.domain.dtos;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
public class CartItem extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;  //how many products of this type you want to add to cart

    @Column(name = "price")
    private BigDecimal price;  // total price/ if quantity = 1 -> price = product.getPrice();
                                            // else price = product.getPrice() * quantity


    public CartItem(Product product, Integer quantity, BigDecimal price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public CartItem() {}

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
