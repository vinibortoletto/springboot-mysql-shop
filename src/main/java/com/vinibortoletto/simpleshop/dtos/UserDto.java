package com.vinibortoletto.simpleshop.dtos;

import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.models.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String phone,
        @NotNull Address address,
        @NotBlank String password,
        @NotNull Role role
) {
}
