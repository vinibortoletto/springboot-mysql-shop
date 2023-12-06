package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

//    public Address save(AddressDto dto, String userId) {
//        Optional<Address> address = addressRepository.findByZipcodeAndNumber(dto.zipcode(), dto.number());
//        User user = userService.findById(userId);
//
//        if (address.isPresent()) {
//            boolean userHasAddress = user.getAddresses().contains(address.get());
//
//            if (userHasAddress) {
//                throw new ConflictException("Address already registered by user");
//            }
//
//            userService.saveUserAddress(address.get(), userId);
//            return address.get();
//        }
//
//        Address newAddress = new Address();
//        BeanUtils.copyProperties(dto, newAddress);
//
//        addressRepository.save(newAddress);
//        userService.saveUserAddress(newAddress, userId);
//
//        return newAddress;
//    }
}