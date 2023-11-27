package com.vinibortoletto.simpleshop.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressDto(
        @NotBlank String street,
        @NotNull Integer number,
        @NotNull Integer zipCode,
        @NotBlank String city,
        @NotBlank String state,
        @NotBlank String country,
        @NotBlank String neighborhood
) {
}
