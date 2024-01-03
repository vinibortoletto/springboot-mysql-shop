package com.vinibortoletto.simpleshop.dtos.address;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDTO(
        @NotBlank String street,
        @NotBlank String number,
        @NotBlank String zipcode,
        @NotBlank String neighborhood,
        @NotBlank String city,
        @NotBlank String state,
        @NotBlank String country,
        @NotBlank String customerId
) {
}
