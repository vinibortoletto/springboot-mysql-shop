package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.AddressDto;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.fakers.AddressFaker;
import com.vinibortoletto.simpleshop.fakers.UserFaker;
import com.vinibortoletto.simpleshop.models.Address;
import com.vinibortoletto.simpleshop.models.User;
import com.vinibortoletto.simpleshop.repositories.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AddressServiceTest {
    private final AddressFaker addressFaker = new AddressFaker();
    private final UserFaker userFaker = new UserFaker();

    @Mock
    private AddressRepository addressRepository;

    @Autowired
    @InjectMocks
    private AddressService addressService;

    @Mock
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("save - should throw exception if address was already registered by user")
    void saveCase1() {
        AddressDto dto = addressFaker.createFakeAddressDto();
        Address address = new Address();
        BeanUtils.copyProperties(dto, address);

        String userId = String.valueOf(UUID.randomUUID());
        User user = userFaker.createFakeUser();
        user.getAddresses().add(address);

        when(addressRepository.findByZipcodeAndNumber(dto.zipcode(), dto.number())).thenReturn(Optional.of(address));
        when(userService.findById(userId)).thenReturn(user);

        assertThrows(ConflictException.class, () -> addressService.save(dto, userId));
        verify(addressRepository, times(1)).findByZipcodeAndNumber(dto.zipcode(), dto.number());
        verify(addressRepository, never()).save(address);
    }

    @Test
    @DisplayName("save - should register user address if address exists but not for user")
    void saveCase2() {
        AddressDto dto = addressFaker.createFakeAddressDto();
        Address address = new Address();
        BeanUtils.copyProperties(dto, address);

        String userId = String.valueOf(UUID.randomUUID());
        User user = userFaker.createFakeUser();

        when(addressRepository.findByZipcodeAndNumber(dto.zipcode(), dto.number())).thenReturn(Optional.of(address));
        when(userService.findById(userId)).thenReturn(user);
        doNothing().when(userService).saveUserAddress(address, userId);

        addressService.save(dto, userId);

        verify(addressRepository, times(1)).findByZipcodeAndNumber(dto.zipcode(), dto.number());
        verify(addressRepository, times(1)).save(address);
    }
}