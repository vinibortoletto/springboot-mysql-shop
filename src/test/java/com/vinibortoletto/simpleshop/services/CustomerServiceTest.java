package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.fakers.AddressFaker;
import com.vinibortoletto.simpleshop.fakers.CustomerFaker;
import com.vinibortoletto.simpleshop.models.Address;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerServiceTest {
    private final CustomerFaker customerFaker = new CustomerFaker();
    private final AddressFaker addressFaker = new AddressFaker();

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findAll - should find all customers")
    void findAllCase1() {
        List<Customer> customerList = List.of(
            customerFaker.createFakeCustomer(),
            customerFaker.createFakeCustomer()
        );

        when(customerRepository.findAll()).thenReturn(customerList);

        List<Customer> foundCustomerList = customerService.findAll();
        assertEquals(customerList, foundCustomerList);
    }

    @Test
    @DisplayName("findById - should find one customer by id")
    void findByIdCase1() {
        Customer customer = customerFaker.createFakeCustomer();

        when(customerRepository.findById(customer.getId())).thenReturn(java.util.Optional.of(customer));

        Customer foundCustomer = customerService.findById(customer.getId());
        assertEquals(customer, foundCustomer);
    }

    @Test
    @DisplayName("findById - should throw NotFoundException when customer not found")
    void findByIdCase2() {
        Customer customer = customerFaker.createFakeCustomer();

        when(customerRepository.findById(customer.getId())).thenReturn(java.util.Optional.empty());
        assertThrows(NotFoundException.class, () -> customerService.findById(customer.getId()));
    }

    @Test
    @DisplayName("saveCustomerAddress - should save customer address")
    void saveCustomerAddressCase1() {
        Customer customer = customerFaker.createFakeCustomer();
        Address address = addressFaker.createFakeAddress();

        customerService.saveCustomerAddress(address, customer);
        verify(customerRepository).save(customer);
    }

}