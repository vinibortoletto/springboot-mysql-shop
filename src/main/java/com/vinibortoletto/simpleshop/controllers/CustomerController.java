package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.customer.CustomerResponseDTO;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "customers")
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @Operation(summary = "Returns all customers")
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> findAll() {
        List<Customer> customerList = service.findAll();
        List<CustomerResponseDTO> response = CustomerResponseDTO.convert(customerList);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Returns a customer based on its id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable String id) {
        Customer customer = service.findById(id);
        CustomerResponseDTO response = new CustomerResponseDTO(customer);

        return ResponseEntity.ok().body(response);
    }
}
