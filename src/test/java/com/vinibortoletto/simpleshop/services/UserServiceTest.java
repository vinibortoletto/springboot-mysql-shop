package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.UserDto;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.fakers.AddressFaker;
import com.vinibortoletto.simpleshop.fakers.UserFaker;
import com.vinibortoletto.simpleshop.models.Address;
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

    private final UserFaker userFaker = new UserFaker();
    private final AddressFaker addressFaker = new AddressFaker();

    @Mock
    private UserRepository repository;

    @Autowired
    @InjectMocks
    private UserService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
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
        List<User> expected = List.of(userFaker.createFakeUser());
        when(repository.findAll()).thenReturn(expected);

        List<User> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("findById - should find one user by id")
    void findByIdCase1() {
        User expected = userFaker.createFakeUser();
        when(repository.findById(expected.getId())).thenReturn(Optional.of(expected));
        User actual = service.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("findById - should throw exception if user is not found")
    void findByIdCase2() {
        User expected = userFaker.createFakeUser();
        when(repository.findById(expected.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.findById(expected.getId()));
    }

    @Test
    @DisplayName("save - should throw exception if user already exists")
    void saveCase1() {
        String expectedMessage = "User already exists in database";
        UserDto dto = userFaker.createFakeUserDto();
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
        UserDto dto = userFaker.createFakeUserDto();
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
        UserDto dto = userFaker.createFakeUserDto();
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
        UserDto dto = userFaker.createFakeUserDto();
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

    @Test
    @DisplayName("delete - should throw exception if user is not found")
    void deleteCase1() {
        String id = String.valueOf(UUID.randomUUID());
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.delete(id));
        verify(repository, times(1)).findById(id);
        verify(repository, never()).deleteById(id);
    }

    @Test
    @DisplayName("delete - should delete an user")
    void deleteCase2() {
        String id = String.valueOf(UUID.randomUUID());
        User user = userFaker.createFakeUser();

        when(repository.findById(id)).thenReturn(Optional.of(user));
        doNothing().when(repository).deleteById(id);

        service.delete(id);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("saveUserAddress - should save address for user")
    void saveUserAddressCase1() {
        String id = String.valueOf(UUID.randomUUID());
        User user = userFaker.createFakeUser();
        Address address = addressFaker.createFakeAddress();

        when(repository.findById(id)).thenReturn(Optional.of(user));
        when(repository.save(user)).thenReturn(user);

        service.saveUserAddress(address, id);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(user);

    }
}