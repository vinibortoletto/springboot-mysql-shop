package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.Address;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(String id) {
        Optional<Customer> user = customerRepository.findById(id);
        return user.orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    public void saveCustomerAddress(Address address, Customer customer) {
        customer.getAddresses().add(address);
        customerRepository.save(customer);
    }
}
