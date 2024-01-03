package com.vinibortoletto.simpleshop.dtos.customer;

import com.vinibortoletto.simpleshop.models.Address;
import com.vinibortoletto.simpleshop.models.Cart;
import com.vinibortoletto.simpleshop.models.Customer;

import java.util.List;

public record CustomerResponseDTO(String id, String name, String email, Cart cart, List<Address> addresses) {
    public CustomerResponseDTO(Customer customer) {
        this(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCart(),
                customer.getAddresses()
        );
    }

    public static List<CustomerResponseDTO> convert(List<Customer> customerList) {
        return customerList.stream().map(CustomerResponseDTO::new).toList();
    }
}
