package com.vinibortoletto.simpleshop.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderRequestDTO(
        @NotBlank @NotNull String customerId,
        @NotBlank @NotNull String shippingAddressId
) {
}
