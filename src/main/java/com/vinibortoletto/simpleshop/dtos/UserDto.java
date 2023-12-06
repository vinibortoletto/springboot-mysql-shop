package com.vinibortoletto.simpleshop.dtos;

import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.models.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserDto(
        @NotBlank String name,
        @NotBlank String email,
        String phone,
        List<Address> addresses,
        @NotBlank String password,
        @NotNull Role role
) {

}
