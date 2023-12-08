package com.vinibortoletto.simpleshop.models;

import com.vinibortoletto.simpleshop.models.pks.CartProductPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

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
    private Integer quantity;
    private BigDecimal subtotal;

    public CartProduct(Cart cart, Product product, Integer quantity, BigDecimal subtotal) {
        id.setCart(cart);
        id.setProduct(product);
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Cart getCart() {
        return id.getCart();
    }

    public void setCart(Cart cart) {
        id.setCart(cart);
    }

    public BigDecimal getSubtotal() {
        return getProduct().getPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }
}
