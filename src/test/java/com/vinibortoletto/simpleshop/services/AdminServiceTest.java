package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.admin.AdminRequestDTO;
import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.fakers.AdminFaker;
import com.vinibortoletto.simpleshop.fakers.UserFaker;
import com.vinibortoletto.simpleshop.models.Admin;
import com.vinibortoletto.simpleshop.models.User;
import com.vinibortoletto.simpleshop.repositories.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AdminServiceTest {

    private final AdminFaker adminFaker = new AdminFaker();
    private final UserFaker userFaker = new UserFaker();


    @InjectMocks
    private AdminService adminService;

    @Mock
    private AdminRepository adminRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findAll - should find all admins")
    void findAllCase2() {
        List<Admin> adminList = List.of(
            adminFaker.createFakeAdmin(),
            adminFaker.createFakeAdmin()
        );

        when(adminRepository.findAll()).thenReturn(adminList);

        List<Admin> foundAdminList = adminService.findAll();
        assertEquals(adminList, foundAdminList);
    }

    @Test
    @DisplayName("findById - should find one admin by id")
    void findByIdCase1() {
        Admin admin = adminFaker.createFakeAdmin();
        when(adminRepository.findById(admin.getId())).thenReturn(Optional.of(admin));

        Admin foundAdmin = adminService.findById(admin.getId());
        assertEquals(admin, foundAdmin);
    }

    @Test
    @DisplayName("findById - should throw exception if admin was not found")
    void findByIdCase2() {
        Admin admin = adminFaker.createFakeAdmin();
        when(adminRepository.findById(admin.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> adminService.findById(admin.getId()));
    }

    @Test
    @DisplayName("save - should save admin")
    void saveCase1() {
        User user = userFaker.createFakeUser(Role.ADMIN);

        Admin admin = new Admin();
        admin.setUser(user);
        admin.setName(user.getName());
        admin.setEmail(user.getEmail());

        when(adminRepository.save(any(Admin.class))).thenReturn(admin);
        Admin savedAdmin = adminService.save(user);

        verify(adminRepository, times(1)).save(any(Admin.class));
        assertEquals(admin, savedAdmin);
    }

    @Test
    @DisplayName("update - should update an admin")
    void updateCase1() {
        Admin admin = adminFaker.createFakeAdmin();
        AdminRequestDTO dto = adminFaker.createFakeAdminRequestDTO();
        BeanUtils.copyProperties(dto, admin);

        when(adminRepository.findById(admin.getId())).thenReturn(Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);

        Admin updatedAdmin = adminService.update(dto, admin.getId());

        verify(adminRepository, times(1)).findById(admin.getId());
        verify(adminRepository, times(1)).save(admin);
        assertEquals(admin, updatedAdmin);
    }

    @Test
    @DisplayName("update - should throw exception if admin is not found")
    void updateCase2() {
        Admin admin = adminFaker.createFakeAdmin();
        AdminRequestDTO dto = adminFaker.createFakeAdminRequestDTO();
        BeanUtils.copyProperties(dto, admin);

        when(adminRepository.findById(admin.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> adminService.update(dto, admin.getId()));
        verify(adminRepository, times(1)).findById(admin.getId());
        verify(adminRepository, never()).save(admin);
    }

    @Test
    @DisplayName("delete - should throw exception if admin was not found")
    void deleteCase1() {
        String id = String.valueOf(UUID.randomUUID());
        when(adminRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> adminService.delete(id));
        verify(adminRepository, times(1)).findById(id);
        verify(adminRepository, never()).deleteById(id);
    }

    @Test
    @DisplayName("delete - should delete an admin")
    void deleteCase2() {
        String id = String.valueOf(UUID.randomUUID());
        Admin admin = adminFaker.createFakeAdmin();

        when(adminRepository.findById(id)).thenReturn(Optional.of(admin));
        doNothing().when(adminRepository).deleteById(id);

        adminService.delete(id);

        verify(adminRepository, times(1)).findById(id);
        verify(adminRepository, times(1)).deleteById(id);
    }
}