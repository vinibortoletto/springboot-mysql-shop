package com.vinibortoletto.simpleshop.dtos;

import com.vinibortoletto.simpleshop.models.Cart;
import com.vinibortoletto.simpleshop.models.Customer;

import java.util.List;

public record CustomerResponseDTO(String id, String name, String email, Cart cart) {
    public CustomerResponseDTO(Customer customer) {
        this(customer.getId(), customer.getName(), customer.getEmail(), customer.getCart());
    }

    public static List<CustomerResponseDTO> convert(List<Customer> customerList) {
        return customerList.stream().map(CustomerResponseDTO::new).toList();
    }
}
