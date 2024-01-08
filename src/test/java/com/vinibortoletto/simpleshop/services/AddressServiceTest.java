package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.address.AddressRequestDTO;
import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.fakers.AddressFaker;
import com.vinibortoletto.simpleshop.fakers.UserFaker;
import com.vinibortoletto.simpleshop.models.Address;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.models.User;
import com.vinibortoletto.simpleshop.repositories.AddressRepository;
import com.vinibortoletto.simpleshop.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AddressServiceTest {
    private final AddressFaker addressFaker = new AddressFaker();
    private final UserFaker userFaker = new UserFaker();

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @Mock
    private UserService userService;

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findAll - should return all addresses")
    void findAllCase1() {
        List<Address> addressList = List.of(
            addressFaker.createFakeAddress(),
            addressFaker.createFakeAddress(),
            addressFaker.createFakeAddress()
        );

        when(addressRepository.findAll()).thenReturn(addressList);

        List<Address> foundAddressList = addressService.findAll();

        assertEquals(addressList, foundAddressList);
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById - should return address by id")
    void findByIdCase1() {
        Address address = addressFaker.createFakeAddress();

        when(addressRepository.findById(address.getId())).thenReturn(Optional.of(address));

        Address foundAddress = addressService.findById(address.getId());

        assertEquals(address, foundAddress);
        verify(addressRepository, times(1)).findById(address.getId());
    }

    @Test
    @DisplayName("findById - should throw exception if address not found")
    void findByIdCase2() {
        Address address = addressFaker.createFakeAddress();

        when(addressRepository.findById(address.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> addressService.findById(address.getId()));
        verify(addressRepository, times(1)).findById(address.getId());
    }

    @Test
    @DisplayName("save - should throw exception if address was already registered by customer")
    void saveCase1() {
        AddressRequestDTO dto = addressFaker.createFakeAddressRequestDTO();
        Address newAddress = new Address();
        BeanUtils.copyProperties(dto, newAddress);

        User user = userFaker.createFakeUser(Role.CUSTOMER);
        Customer customer = user.getCustomer();
        customer.getAddresses().add(newAddress);

        when(addressRepository.findByZipcodeAndNumber(dto.zipcode(), dto.number()))
            .thenReturn(Optional.of(newAddress));

        when(customerService.findById(dto.customerId()))
            .thenReturn(customer);

        assertThrows(ConflictException.class, () -> addressService.save(dto));

        verify(addressRepository, times(1))
            .findByZipcodeAndNumber(dto.zipcode(), dto.number());

        verify(customerService, times(1))
            .findById(dto.customerId());

        verify(addressRepository, never()).save(newAddress);
    }

    @Test
    @DisplayName("save - should register customer address if address exists but not for customer")
    void saveCase2() {
        AddressRequestDTO dto = addressFaker.createFakeAddressRequestDTO();
        Address newAddress = new Address();
        BeanUtils.copyProperties(dto, newAddress);

        User user = userFaker.createFakeUser(Role.CUSTOMER);
        Customer customer = user.getCustomer();

        when(addressRepository.findByZipcodeAndNumber(dto.zipcode(), dto.number()))
            .thenReturn(Optional.of(newAddress));

        when(customerService.findById(dto.customerId()))
            .thenReturn(customer);

        doNothing().when(customerService).saveCustomerAddress(newAddress, customer);

        Address savedAddress = addressService.save(dto);

        verify(addressRepository, times(1))
            .findByZipcodeAndNumber(dto.zipcode(), dto.number());

        verify(customerService, times(1))
            .findById(dto.customerId());

        verify(customerService, times(1))
            .saveCustomerAddress(newAddress, customer);

        assertEquals(newAddress, savedAddress);
    }

    @Test
    @DisplayName("save - should register user address if address does not exists")
    void saveCase3() {
        AddressRequestDTO dto = addressFaker.createFakeAddressRequestDTO();
        Address newAddress = new Address();
        BeanUtils.copyProperties(dto, newAddress);

        User user = userFaker.createFakeUser(Role.CUSTOMER);
        Customer customer = user.getCustomer();

        when(addressRepository.findByZipcodeAndNumber(dto.zipcode(), dto.number()))
            .thenReturn(Optional.empty());

        when(customerService.findById(dto.customerId()))
            .thenReturn(customer);

        when(addressRepository.save(newAddress)).thenReturn(newAddress);
        doNothing().when(customerService).saveCustomerAddress(newAddress, customer);

        Address savedAddress = addressService.save(dto);

        verify(addressRepository, times(1))
            .findByZipcodeAndNumber(dto.zipcode(), dto.number());

        verify(customerService, times(1))
            .findById(dto.customerId());

        verify(addressRepository, times(1))
            .save(newAddress);

        verify(customerService, times(1))
            .saveCustomerAddress(newAddress, customer);

        assertEquals(newAddress, savedAddress);
    }

    @Test
    @DisplayName("update - should update address")
    void updateCase1() {
        Address address = addressFaker.createFakeAddress();
        AddressRequestDTO dto = addressFaker.createFakeAddressRequestDTO();
        Address newAddress = new Address();
        BeanUtils.copyProperties(dto, newAddress);

        when(addressRepository.findById(address.getId())).thenReturn(Optional.of(address));
        when(addressRepository.save(newAddress)).thenReturn(newAddress);

        Address updatedAddress = addressService.update(dto, address.getId());

        assertEquals(newAddress, updatedAddress);
        verify(addressRepository, times(1)).findById(address.getId());
        verify(addressRepository, times(1)).save(newAddress);
    }

    @Test
    @DisplayName("update - should throw exception if address not found")
    void updateCase2() {
        Address address = addressFaker.createFakeAddress();
        AddressRequestDTO dto = addressFaker.createFakeAddressRequestDTO();
        Address newAddress = new Address();
        BeanUtils.copyProperties(dto, newAddress);

        when(addressRepository.findById(address.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> addressService.findById(address.getId()));
        verify(addressRepository, times(1)).findById(address.getId());
        verify(addressRepository, never()).save(newAddress);
    }
}