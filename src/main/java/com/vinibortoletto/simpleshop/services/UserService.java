package com.vinibortoletto.simpleshop.services;


import com.vinibortoletto.simpleshop.dtos.user.UserRequestDTO;
import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.DatabaseException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.Admin;
import com.vinibortoletto.simpleshop.models.Cart;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.models.User;
import com.vinibortoletto.simpleshop.repositories.CustomerRepository;
import com.vinibortoletto.simpleshop.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    CustomerService customerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminService adminService;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Customer saveCustomer(User user) {
        Customer customer = new Customer();

        customer.setUser(user);
        customer.setName(user.getName());
        customer.setEmail(user.getEmail());

        Cart cart = cartService.save(customer);
        customer.setCart(cart);

        return customerRepository.save(customer);
    }

    public User save(UserRequestDTO dto) {

        Optional<User> user = userRepository.findByEmail(dto.email());

        if (user.isPresent()) {
            throw new ConflictException("Email already already in use");
        }

        User newUser = new User();
        BeanUtils.copyProperties(dto, newUser);

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        newUser.setPassword(encryptedPassword);

        User savedUser = userRepository.save(newUser);

        if (savedUser.getRole() == Role.CUSTOMER) {
            Customer savedCustomer = saveCustomer(savedUser);
            savedUser.setCustomer(savedCustomer);
        } else {
            Admin savedAdmin = adminService.save(savedUser);
            savedUser.setAdmin(savedAdmin);
        }

        return savedUser;
    }

    public User update(UserRequestDTO dto, String id) {
        User user = findById(id);
        BeanUtils.copyProperties(dto, user);
        return userRepository.save(user);
    }

    public void delete(String id) {
        findById(id);

        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
