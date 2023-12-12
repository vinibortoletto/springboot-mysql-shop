package com.vinibortoletto.simpleshop.dtos;

import com.vinibortoletto.simpleshop.models.CartProduct;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record CartRequestDTO(
        @NotBlank String customerId,
        @NotBlank String cartId,
        @NotEmpty Set<CartProduct> cartProductSet
) {
}
