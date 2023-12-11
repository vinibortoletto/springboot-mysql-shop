package com.vinibortoletto.simpleshop.dtos;

import com.vinibortoletto.simpleshop.models.Admin;

import java.util.List;

public record AdminResponseDTO(String id, String name, String email) {
    public AdminResponseDTO(Admin admin) {
        this(admin.getId(), admin.getName(), admin.getEmail());
    }

    public static List<AdminResponseDTO> convert(List<Admin> adminList) {
        return adminList.stream().map(AdminResponseDTO::new).toList();
    }
}
