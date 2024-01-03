package com.vinibortoletto.simpleshop.dtos.cartProduct;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CartProductRequestDTO(
        @NotBlank String id,
        @NotNull Integer quantity
) {
}
