package com.vinibortoletto.simpleshop.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_cart_product")
public class CartProduct implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CartProductPK id = new CartProductPK();
    private Double subtotal;
    private Integer quantity;

    public Cart getCart() {
        return id.getCart();
    }

    public void setCart(Cart cart) {
        id.setCart(cart);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Double getSubtotal() {
        return getProduct().getPrice() * getQuantity();
    }
}

