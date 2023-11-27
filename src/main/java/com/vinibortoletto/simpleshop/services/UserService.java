package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.User;
import com.vinibortoletto.simpleshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new NotFoundException("User not found"));
    }
}
