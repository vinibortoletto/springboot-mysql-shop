package com.vinibortoletto.simpleshop.dtos;

import jakarta.validation.constraints.NotBlank;

public record AddressDto(
        @NotBlank String street,
        @NotBlank String number,
        @NotBlank String zipCode,
        @NotBlank String city,
        @NotBlank String state,
        @NotBlank String country,
        @NotBlank String neighborhood
) {
}
