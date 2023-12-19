package com.vinibortoletto.simpleshop.dtos.admin;

import jakarta.validation.constraints.NotBlank;

public record AdminRequestDTO(
        @NotBlank String name,
        @NotBlank String email
) {
}
