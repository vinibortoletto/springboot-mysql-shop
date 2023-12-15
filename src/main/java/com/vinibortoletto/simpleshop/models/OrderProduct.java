package com.vinibortoletto.simpleshop.models;

import com.vinibortoletto.simpleshop.models.pks.OrderProductPK;
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
@Table(name = "tb_order_product")
public class OrderProduct implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderProductPK id = new OrderProductPK();
    private Integer quantity;
    private BigDecimal subtotal;

    public OrderProduct(Order order, Product product, Integer quantity, BigDecimal subtotal) {
        id.setOrder(order);
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

    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public BigDecimal getSubtotal() {
        return getProduct().getPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }

}
