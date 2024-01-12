package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.customer.CustomerResponseDTO;
import com.vinibortoletto.simpleshop.fakers.CustomerFaker;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.services.CustomerService;
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

class CustomerControllerTest {
    private final CustomerFaker customerFaker = new CustomerFaker();

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findAll - should return all customers")
    void findAll() {
        List<Customer> customerList = List.of(customerFaker.createFakeCustomer());
        when(customerService.findAll()).thenReturn(customerList);

        ResponseEntity<List<CustomerResponseDTO>> expected = ResponseEntity
            .ok()
            .body(CustomerResponseDTO.convert(customerList));

        ResponseEntity<List<CustomerResponseDTO>> response = customerController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customerList.size(), Objects.requireNonNull(response.getBody()).size());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("findById - should return a customer based on its id")
    void findById() {
        Customer customer = customerFaker.createFakeCustomer();
        when(customerService.findById(customer.getId())).thenReturn(customer);

        ResponseEntity<CustomerResponseDTO> expected = ResponseEntity
            .ok()
            .body(new CustomerResponseDTO(customer));

        ResponseEntity<CustomerResponseDTO> response = customerController.findById(customer.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response);
    }
}