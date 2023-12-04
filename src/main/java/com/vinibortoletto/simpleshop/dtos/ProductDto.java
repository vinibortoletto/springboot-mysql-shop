package com.vinibortoletto.simpleshop.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ProductDto(
        @NotBlank String name,
        @NotNull BigDecimal price,
        @NotNull Integer stock,
        @NotBlank String image,
        @NotBlank String description,
        @NotEmpty List<String> categories
) {
}
