package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.admin.AdminResponseDTO;
import com.vinibortoletto.simpleshop.fakers.AdminFaker;
import com.vinibortoletto.simpleshop.models.Admin;
import com.vinibortoletto.simpleshop.services.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AdminControllerTest {
    private final AdminFaker adminFaker = new AdminFaker();

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController = new AdminController();

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findAll - should return all admins")
    void findAll() {
        List<Admin> adminList = List.of(adminFaker.createFakeAdmin());
        when(adminService.findAll()).thenReturn(adminList);

        ResponseEntity<List<AdminResponseDTO>> expected = ResponseEntity
            .ok()
            .body(AdminResponseDTO.convert(adminList));

        ResponseEntity<List<AdminResponseDTO>> response = adminController.findAll();

        assertEquals(expected, response);
        assertEquals(adminList.size(), Objects.requireNonNull(response.getBody()).size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}