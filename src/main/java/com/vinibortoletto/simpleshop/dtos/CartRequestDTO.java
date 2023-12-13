package com.vinibortoletto.simpleshop.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record CartRequestDTO(
        @NotBlank String customerId,
        @NotBlank String cartId,
        @NotEmpty Set<CartProductRequestDTO> products
) {
}
