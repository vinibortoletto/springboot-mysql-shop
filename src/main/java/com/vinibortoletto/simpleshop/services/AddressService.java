package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.address.AddressRequestDTO;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.Address;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.repositories.AddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerService customerService;

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(String id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.orElseThrow(() -> new NotFoundException("Address not found"));
    }

    public Address save(AddressRequestDTO dto) {
        Optional<Address> address = addressRepository.findByZipcodeAndNumber(dto.zipcode(), dto.number());
        Customer customer = customerService.findById(dto.customerId());

        if (address.isPresent()) {
            boolean customerHasAddress = customer.getAddresses().contains(address.get());


            if (customerHasAddress) {
                throw new ConflictException("Address already registered by customer");
            }

            customerService.saveCustomerAddress(address.get(), customer);
            return address.get();
        }

        Address newAddress = new Address();
        BeanUtils.copyProperties(dto, newAddress);

        Address savedAddress = addressRepository.save(newAddress);
        customerService.saveCustomerAddress(savedAddress, customer);

        return savedAddress;
    }


    public Address update(AddressRequestDTO dto, String id) {
        findById(id);

        Address newAddress = new Address();
        BeanUtils.copyProperties(dto, newAddress);

        return addressRepository.save(newAddress);
    }
}