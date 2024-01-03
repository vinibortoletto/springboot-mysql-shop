package com.vinibortoletto.simpleshop.dtos.user;

import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.models.User;

import java.util.List;

public record UserResponseDTO(String id, String name, String email, Role role) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    public static List<UserResponseDTO> convert(List<User> userList) {
        return userList.stream().map(UserResponseDTO::new).toList();
    }
}
