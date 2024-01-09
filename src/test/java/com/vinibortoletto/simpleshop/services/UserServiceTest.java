package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.user.UserRequestDTO;
import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.DatabaseException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.fakers.AddressFaker;
import com.vinibortoletto.simpleshop.fakers.CartFaker;
import com.vinibortoletto.simpleshop.fakers.UserFaker;
import com.vinibortoletto.simpleshop.models.User;
import com.vinibortoletto.simpleshop.repositories.CustomerRepository;
import com.vinibortoletto.simpleshop.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    private final CartFaker cartFaker = new CartFaker();

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AdminService adminService;

    @InjectMocks
    private UserService userService;

    @Mock
    private CartService cartService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findAll - should return empty array")
    void findAllCase1() {
        List<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        List<User> foundUserList = userService.findAll();
        assertEquals(userList, foundUserList);
    }

    @Test
    @DisplayName("findAll - should find all users")
    void findAllCase2() {
        List<User> userList = List.of(
            userFaker.createFakeUser(Role.CUSTOMER),
            userFaker.createFakeUser(Role.ADMIN)
        );

        when(userRepository.findAll()).thenReturn(userList);

        List<User> foundUserList = userService.findAll();
        assertEquals(userList, foundUserList);
    }

    @Test
    @DisplayName("findById - should find one user by id")
    void findByIdCase1() {
        User user = userFaker.createFakeUser(Role.CUSTOMER);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User foundUser = userService.findById(user.getId());
        assertEquals(user, foundUser);
    }

    @Test
    @DisplayName("findById - should throw exception if user is not found")
    void findByIdCase2() {
        User user = userFaker.createFakeUser(Role.CUSTOMER);
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.findById(user.getId()));
    }

    @Test
    @DisplayName("findByEmail - should find one user by email")
    void findByEmailCase1() {
        User user = userFaker.createFakeUser(Role.CUSTOMER);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        User foundUser = userService.findByEmail(user.getEmail());
        assertEquals(user, foundUser);
    }

    @Test
    @DisplayName("findByEmail - should throw exception if user is not found")
    void findByEmailCase2() {
        User user = userFaker.createFakeUser(Role.CUSTOMER);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.findByEmail(user.getEmail()));
    }

    @Test
    @DisplayName("save - should throw exception if user already exists")
    void saveCase1() {
        UserRequestDTO dto = userFaker.createFakeUserRequestDTO(Role.CUSTOMER);
        User user = new User();
        BeanUtils.copyProperties(dto, user);

        when(userRepository.findByEmail(dto.email())).thenReturn(Optional.of(user));
        assertThrows(ConflictException.class, () -> userService.save(dto));
        verify(userRepository, times(1)).findByEmail(dto.email());
        verify(userRepository, never()).save(user);
    }

    @Test
    @DisplayName("save - should save a customer")
    void saveCase2() {
        UserRequestDTO dto = userFaker.createFakeUserRequestDTO(Role.CUSTOMER);
        User newUser = new User();
        BeanUtils.copyProperties(dto, newUser);

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        newUser.setPassword(encryptedPassword);

        when(userRepository.findByEmail(dto.email())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User savedUser = userService.save(dto);

        verify(userRepository, times(1)).findByEmail(dto.email());
        verify(userRepository, times(1)).save(any(User.class));
        assertEquals(newUser, savedUser);
    }

    @Test
    @DisplayName("save - should save an admin")
    void saveCase3() {
        UserRequestDTO dto = userFaker.createFakeUserRequestDTO(Role.ADMIN);
        User newUser = new User();
        BeanUtils.copyProperties(dto, newUser);

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        newUser.setPassword(encryptedPassword);

        when(userRepository.findByEmail(dto.email())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User savedUser = userService.save(dto);

        verify(userRepository, times(1)).findByEmail(dto.email());
        verify(userRepository, times(1)).save(any(User.class));
        assertEquals(newUser, savedUser);
    }

    @Test
    @DisplayName("update - should throw exception if user is not found")
    void updateCase1() {
        UserRequestDTO dto = userFaker.createFakeUserRequestDTO(Role.CUSTOMER);
        String id = String.valueOf(UUID.randomUUID());
        User newUser = new User();
        BeanUtils.copyProperties(dto, newUser);

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.update(dto, id));
        verify(userRepository, times(1)).findById(id);
        verify(userRepository, never()).save(newUser);
    }

    @Test
    @DisplayName("update - should update an user")
    void updateCase2() {
        UserRequestDTO dto = userFaker.createFakeUserRequestDTO(Role.CUSTOMER);
        String id = String.valueOf(UUID.randomUUID());
        User newUser = new User();
        BeanUtils.copyProperties(dto, newUser);

        when(userRepository.findById(id)).thenReturn(Optional.of(newUser));
        when(userRepository.save(newUser)).thenReturn(newUser);

        User updatedUser = userService.update(dto, id);

        assertEquals(newUser, updatedUser);
        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    @DisplayName("delete - should throw exception if user is not found")
    void deleteCase1() {
        String id = String.valueOf(UUID.randomUUID());
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.delete(id));
        verify(userRepository, times(1)).findById(id);
        verify(userRepository, never()).deleteById(id);
    }

    @Test
    @DisplayName("delete - should delete an user")
    void deleteCase2() {
        String id = String.valueOf(UUID.randomUUID());
        User user = userFaker.createFakeUser(Role.CUSTOMER);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(id);

        userService.delete(id);

        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("delete - should throw DataIntegrityViolationException if user is in use")
    void deleteCase3() {
        String id = String.valueOf(UUID.randomUUID());
        User user = userFaker.createFakeUser(Role.CUSTOMER);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        doThrow(DataIntegrityViolationException.class).when(userRepository).deleteById(id);

        assertThrows(DatabaseException.class, () -> userService.delete(id));
    }
}