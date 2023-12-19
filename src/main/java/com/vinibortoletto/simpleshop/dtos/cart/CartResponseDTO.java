package com.vinibortoletto.simpleshop.dtos.cart;

import com.vinibortoletto.simpleshop.dtos.cartProduct.CartProductResponseDTO;
import com.vinibortoletto.simpleshop.models.Cart;

import java.math.BigDecimal;
import java.util.List;

public record CartResponseDTO(
        String id,
        BigDecimal total,
        String customerId,
        List<CartProductResponseDTO> products
) {
    public CartResponseDTO(Cart cart) {
        this(
                cart.getId(),
                cart.getTotal(),
                cart.getCustomer().getId(),
                CartProductResponseDTO.convert(cart.getProducts())
        );
    }

    public static List<CartResponseDTO> convert(List<Cart> carts) {
        return carts.stream().map(CartResponseDTO::new).toList();
    }
}
