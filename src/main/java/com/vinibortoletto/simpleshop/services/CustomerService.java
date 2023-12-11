package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.CustomerRequestDTO;
import com.vinibortoletto.simpleshop.exceptions.DatabaseException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.Address;
import com.vinibortoletto.simpleshop.models.Cart;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.models.User;
import com.vinibortoletto.simpleshop.repositories.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartService cartService;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(String id) {
        Optional<Customer> user = customerRepository.findById(id);
        return user.orElseThrow(() -> new NotFoundException("Customer not found"));
    }

//    public CustomerResponseDTO save(CustomerRequestDTO dto) {
//        Optional<Customer> customer = customerRepository.findByEmail(dto.email());
//
//        if (customer.isPresent()) {
//            throw new ConflictException("Email already exists in database");
//        }
//
//        Customer newCustomer = new Customer();
//        BeanUtils.copyProperties(dto, newCustomer);
//
//        Cart cart = cartService.save(newCustomer);
//        Address address = new Address();
//
//        newCustomer.setCart(cart);
//        newCustomer.getAddresses().add(address);
//
//        Customer savedCustomer = customerRepository.save(newCustomer);
//        return new CustomerResponseDTO(savedCustomer);
//    }

    public Customer save(User user) {
        Customer customer = new Customer();

        customer.setUser(user);
        customer.setName(user.getName());
        customer.setEmail(user.getEmail());

        Cart cart = cartService.save(customer);
        customer.setCart(cart);

        return customerRepository.save(customer);
    }

    public Customer update(CustomerRequestDTO dto, String id) {
        Customer user = findById(id);
        BeanUtils.copyProperties(dto, user);
        return customerRepository.save(user);
    }

    public void delete(String id) {
        findById(id);

        try {
            customerRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void saveCustomerAddress(Address address, String userId) {
        Customer user = findById(userId);
        user.getAddresses().add(address);
        customerRepository.save(user);
    }
}
