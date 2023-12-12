package com.vinibortoletto.simpleshop.dtos;

import com.vinibortoletto.simpleshop.models.Cart;
import com.vinibortoletto.simpleshop.models.CartProduct;

import java.util.List;
import java.util.Set;

public record CartResponseDTO(
        String id,
        String customerId,
        Set<CartProduct> products
) {
    public CartResponseDTO(Cart cart) {
        this(
                cart.getId(),
                cart.getCustomer().getId(),
                cart.getProducts()
        );
    }

    public static List<CartResponseDTO> convert(Set<Cart> carts) {
        return carts.stream().map(CartResponseDTO::new).toList();
    }
}
