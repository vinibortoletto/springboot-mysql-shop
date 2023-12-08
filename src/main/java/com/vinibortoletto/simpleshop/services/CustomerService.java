package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.User;
import com.vinibortoletto.simpleshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAllByRole(Role.CUSTOMER);
    }

    public User findById(String id) {
        Optional<User> customer = repository.findById(id);

        if (customer.isEmpty() || customer.get().getRole() != Role.CUSTOMER) {
            throw new NotFoundException("Customer not found");
        }

        return customer.get();
    }
}
