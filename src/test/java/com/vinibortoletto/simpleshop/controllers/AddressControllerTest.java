package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.address.AddressRequestDTO;
import com.vinibortoletto.simpleshop.dtos.address.AddressResponseDTO;
import com.vinibortoletto.simpleshop.fakers.AddressFaker;
import com.vinibortoletto.simpleshop.fakers.CustomerFaker;
import com.vinibortoletto.simpleshop.models.Address;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.services.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AddressControllerTest {

    private final AddressFaker addressFaker = new AddressFaker();
    private final CustomerFaker customerFaker = new CustomerFaker();

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findAll - should return all addresses")
    void findAll() {
        List<Address> addressList = List.of(addressFaker.createFakeAddress());
        when(addressService.findAll()).thenReturn(addressList);

        ResponseEntity<List<AddressResponseDTO>> expected = ResponseEntity
            .ok()
            .body(AddressResponseDTO.convert(addressList));

        ResponseEntity<List<AddressResponseDTO>> response = addressController.findAll();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(addressList.size(), Objects.requireNonNull(response.getBody()).size());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("findById - should return an address based on its id")
    void findById() {
        Address address = addressFaker.createFakeAddress();
        when(addressService.findById(address.getId())).thenReturn(address);

        ResponseEntity<AddressResponseDTO> expected = ResponseEntity
            .ok()
            .body(new AddressResponseDTO(address));

        ResponseEntity<AddressResponseDTO> response = addressController.findById(address.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response);
    }


    @Test
    @DisplayName("findAllByCustomerId - should return all addresses based on the customer id")
    void findAllByCustomerId() {
        Customer customer = customerFaker.createFakeCustomer();

        when(addressService.findAllByCustomerId(customer.getId()))
            .thenReturn(customer.getAddresses());

        ResponseEntity<List<AddressResponseDTO>> expected = ResponseEntity
            .ok()
            .body(AddressResponseDTO.convert(customer.getAddresses()));

        ResponseEntity<List<AddressResponseDTO>> response = addressController
            .findAllByCustomerId(customer.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("save - should create a new address for user")
    void save() {
        AddressRequestDTO dto = addressFaker.createFakeAddressRequestDTO();
        Address address = new Address();
        BeanUtils.copyProperties(dto, address);

        when(addressService.save(dto)).thenReturn(address);

        ResponseEntity<AddressResponseDTO> expected = ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new AddressResponseDTO(address));

        ResponseEntity<AddressResponseDTO> response = addressController.save(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("update - should update an user address")
    void update() {
        AddressRequestDTO dto = addressFaker.createFakeAddressRequestDTO();
        Address address = new Address();
        BeanUtils.copyProperties(dto, address);

        when(addressService.update(dto, address.getId())).thenReturn(address);

        ResponseEntity<AddressResponseDTO> expected = ResponseEntity
            .ok()
            .body(new AddressResponseDTO(address));

        ResponseEntity<AddressResponseDTO> response = addressController
            .update(dto, address.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response);
    }
}