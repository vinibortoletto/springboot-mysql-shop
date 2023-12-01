package com.vinibortoletto.simpleshop.services;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.dtos.UserDto;
import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.User;
import com.vinibortoletto.simpleshop.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

    private UserDto createFakeUserDto() {
        return new UserDto(
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.phoneNumber().phoneNumber(),
                new ArrayList<>(),
                faker.internet().password(),
                Role.SELLER
        );
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

    @Test
    @DisplayName("save - should throw exception if user already exists")
    void saveCase1() {
        String expectedMessage = "User already exists in database";
        UserDto dto = createFakeUserDto();
        User user = new User();
        BeanUtils.copyProperties(dto, user);

        when(repository.findByEmail(dto.email())).thenReturn(Optional.of(user));
        assertThrows(ConflictException.class, () -> service.save(dto));
        verify(repository, times(1)).findByEmail(dto.email());
        verify(repository, never()).save(user);
    }

    @Test
    @DisplayName("save - should save a user")
    void saveCase2() {
        UserDto dto = createFakeUserDto();
        User expected = new User();
        BeanUtils.copyProperties(dto, expected);

        when(repository.findByEmail(dto.email())).thenReturn(Optional.empty());
        when(repository.save(expected)).thenReturn(expected);

        User actual = service.save(dto);
        verify(repository, times(1)).findByEmail(dto.email());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("update - should throw exception if user is not found")
    void updateCase1() {
        UserDto dto = createFakeUserDto();
        String id = String.valueOf(UUID.randomUUID());
        User newUser = new User();
        BeanUtils.copyProperties(dto, newUser);

        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.update(dto, id));
        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(newUser);
    }

    @Test
    @DisplayName("update - should update an user")
    void updateCase2() {
        UserDto dto = createFakeUserDto();
        String id = String.valueOf(UUID.randomUUID());
        User newUser = new User();
        BeanUtils.copyProperties(dto, newUser);

        when(repository.findById(id)).thenReturn(Optional.of(newUser));
        when(repository.save(newUser)).thenReturn(newUser);

        User updatedUser = service.update(dto, id);

        assertEquals(newUser, updatedUser);
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(newUser);
    }
}