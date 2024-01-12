package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.user.UserRequestDTO;
import com.vinibortoletto.simpleshop.dtos.user.UserResponseDTO;
import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.fakers.UserFaker;
import com.vinibortoletto.simpleshop.models.User;
import com.vinibortoletto.simpleshop.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserControllerTest {
    private final UserFaker userFaker = new UserFaker();


    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findAll - should return all users")
    void findAll() {
        List<User> userList = List.of(userFaker.createFakeUser(Role.CUSTOMER));
        when(userService.findAll()).thenReturn(userList);

        ResponseEntity<List<UserResponseDTO>> expected = ResponseEntity
            .ok()
            .body(UserResponseDTO.convert(userList));

        ResponseEntity<List<UserResponseDTO>> response = userController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList.size(), Objects.requireNonNull(response.getBody()).size());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("findById - should return a user by id")
    void findById() {
        User user = userFaker.createFakeUser(Role.CUSTOMER);
        when(userService.findById(user.getId())).thenReturn(user);

        ResponseEntity<UserResponseDTO> expected = ResponseEntity
            .ok()
            .body(new UserResponseDTO(user));

        ResponseEntity<UserResponseDTO> response = userController.findById(user.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("save - should save a user")
    void save() {
        UserRequestDTO dto = userFaker.createFakeUserRequestDTO(Role.CUSTOMER);
        User user = userFaker.createFakeUser(Role.CUSTOMER);
        BeanUtils.copyProperties(dto, user);

        when(userService.save(dto)).thenReturn(user);

        ResponseEntity<UserResponseDTO> expected = ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new UserResponseDTO(user));

        ResponseEntity<UserResponseDTO> response = userController.save(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("update - should update a user")
    void update() {
        UserRequestDTO dto = userFaker.createFakeUserRequestDTO(Role.CUSTOMER);
        User user = userFaker.createFakeUser(Role.CUSTOMER);
        BeanUtils.copyProperties(dto, user);

        when(userService.update(dto, user.getId())).thenReturn(user);

        ResponseEntity<UserResponseDTO> expected = ResponseEntity
            .ok()
            .body(new UserResponseDTO(user));

        ResponseEntity<UserResponseDTO> response = userController.update(dto, user.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("delete - should delete a user")
    void delete() {
        User user = userFaker.createFakeUser(Role.CUSTOMER);

        ResponseEntity<Void> expected = ResponseEntity
            .noContent()
            .build();

        ResponseEntity<Void> response = userController.delete(user.getId());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(expected, response);
    }
}