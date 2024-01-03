package com.vinibortoletto.simpleshop.dtos.cart;

import com.vinibortoletto.simpleshop.dtos.cartProduct.CartProductRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record CartRequestDTO(
        @NotBlank String customerId,
        @NotEmpty Set<CartProductRequestDTO> products
) {
}
