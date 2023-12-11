package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.AddressRequestDTO;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.models.Address;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.repositories.AddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerService customerService;

    public Address save(AddressRequestDTO dto, String customerId) {
        Optional<Address> address = addressRepository.findByZipcodeAndNumber(dto.zipcode(), dto.number());
        Customer customer = customerService.findById(customerId);

        if (address.isPresent()) {
            boolean customerHasAddress = customer.getAddresses().contains(address.get());

            if (customerHasAddress) {
                throw new ConflictException("Address already registered by customer");
            }

            customerService.saveCustomerAddress(address.get(), customerId);
            return address.get();
        }

        Address newAddress = new Address();
        BeanUtils.copyProperties(dto, newAddress);

        addressRepository.save(newAddress);
        customerService.saveCustomerAddress(newAddress, customerId);

        return newAddress;
    }
}