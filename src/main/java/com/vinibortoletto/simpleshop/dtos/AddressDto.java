package com.vinibortoletto.simpleshop.dtos;

import jakarta.validation.constraints.NotBlank;

public record AddressDto(
        @NotBlank String street,
        @NotBlank String number,
        @NotBlank String zipcode,
        @NotBlank String neighborhood,
        @NotBlank String city,
        @NotBlank String state,
        @NotBlank String country
) {
}
