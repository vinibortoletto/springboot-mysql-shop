package com.vinibortoletto.simpleshop.dtos;

import com.vinibortoletto.simpleshop.models.Cart;

import java.util.List;
import java.util.Set;

public record CartResponseDTO(
        String id,
        String customerId,
        List<CartProductResponseDTO> products
) {
    public CartResponseDTO(Cart cart) {
        this(
                cart.getId(),
                cart.getCustomer().getId(),
                CartProductResponseDTO.convert(cart.getProducts())
        );
    }

    public static List<CartResponseDTO> convert(Set<Cart> carts) {
        return carts.stream().map(CartResponseDTO::new).toList();
    }
}
