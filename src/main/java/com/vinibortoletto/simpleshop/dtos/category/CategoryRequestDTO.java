package com.vinibortoletto.simpleshop.dtos.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank String name
) {
}
