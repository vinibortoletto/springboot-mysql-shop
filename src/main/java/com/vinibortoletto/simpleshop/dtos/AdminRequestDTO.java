package com.vinibortoletto.simpleshop.dtos;

import jakarta.validation.constraints.NotBlank;

public record AdminRequestDTO(
        @NotBlank String name,
        @NotBlank String email
) {
}
