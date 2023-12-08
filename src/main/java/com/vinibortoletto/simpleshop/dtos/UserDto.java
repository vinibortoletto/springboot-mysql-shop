package com.vinibortoletto.simpleshop.dtos;

import com.vinibortoletto.simpleshop.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(
        @NotBlank String name,
        @NotBlank String email,
        String phone,
        @NotBlank String password,
        @NotNull Role role
) {

}
