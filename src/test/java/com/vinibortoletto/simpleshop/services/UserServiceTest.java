package com.vinibortoletto.simpleshop.services;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.User;
import com.vinibortoletto.simpleshop.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserServiceTest {
    private final Faker faker = new Faker();

    @Mock
    private UserRepository repository;

    @Autowired
    @InjectMocks
    private UserService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private User createFakeUser() {
        User fakeUser = new User();

        fakeUser.setId(String.valueOf(UUID.randomUUID()));
        fakeUser.setName(faker.commerce().productName());
        fakeUser.setEmail(faker.internet().emailAddress());
        fakeUser.setPhone(faker.phoneNumber().phoneNumber());
        fakeUser.setPassword(faker.internet().password());
        fakeUser.setRole(Role.SELLER);

        return fakeUser;
    }

    @Test
    @DisplayName("findAll - should return empty array")
    void findAllCase1() {
        List<User> expected = new ArrayList<>();
        when(repository.findAll()).thenReturn(expected);

        List<User> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("findAll - should find all users")
    void findAllCase2() {
        List<User> expected = List.of(createFakeUser());
        when(repository.findAll()).thenReturn(expected);

        List<User> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("findById - should find one user by id")
    void findByIdCase1() {
        User expected = createFakeUser();
        when(repository.findById(expected.getId())).thenReturn(Optional.of(expected));
        User actual = service.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("findById - should throw exception if user is not found")
    void findByIdCase2() {
        User expected = createFakeUser();
        when(repository.findById(expected.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.findById(expected.getId()));
    }
}