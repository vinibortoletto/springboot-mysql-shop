package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.admin.AdminResponseDTO;
import com.vinibortoletto.simpleshop.models.Admin;
import com.vinibortoletto.simpleshop.services.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "admins")
@RestController
@RequestMapping(value = "/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Operation(summary = "Returns all admins")
    @GetMapping
    public ResponseEntity<List<AdminResponseDTO>> findAll() {
        List<Admin> adminList = adminService.findAll();
        List<AdminResponseDTO> response = AdminResponseDTO.convert(adminList);

        return ResponseEntity.ok().body(response);
    }
}
