package com.vinibortoletto.simpleshop.dtos.cartProduct;

import com.vinibortoletto.simpleshop.models.CartProduct;
import com.vinibortoletto.simpleshop.models.Product;

import java.math.BigDecimal;
import java.util.List;

public record CartProductResponseDTO(
        Integer quantity,
        BigDecimal subtotal,
        Product product
) {

    public CartProductResponseDTO(CartProduct cartProduct) {
        this(
                cartProduct.getQuantity(),
                cartProduct.getSubtotal(),
                cartProduct.getProduct()
        );
    }

    public static List<CartProductResponseDTO> convert(List<CartProduct> cartProducts) {
        return cartProducts.stream().map(CartProductResponseDTO::new).toList();
    }
}
