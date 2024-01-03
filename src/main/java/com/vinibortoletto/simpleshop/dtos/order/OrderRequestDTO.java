package com.vinibortoletto.simpleshop.dtos.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderRequestDTO(
        @NotBlank @NotNull String customerId,
        @NotBlank @NotNull String shippingAddressId
) {
}
