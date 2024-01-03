package com.vinibortoletto.simpleshop.dtos.customer;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDTO(
        @NotBlank String name,
        @NotBlank String email
) {
}
