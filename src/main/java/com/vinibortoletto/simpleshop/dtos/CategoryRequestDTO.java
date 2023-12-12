package com.vinibortoletto.simpleshop.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank String name
) {
}
