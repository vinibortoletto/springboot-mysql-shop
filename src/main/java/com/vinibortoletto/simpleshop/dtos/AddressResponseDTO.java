package com.vinibortoletto.simpleshop.dtos;

import com.vinibortoletto.simpleshop.models.Address;

import java.util.List;

public record AddressResponseDTO(
        String street,
        String number,
        String zipcode,
        String neighborhood,
        String city,
        String state,
        String country,
        String customerId
) {
    public AddressResponseDTO(Address address, String customerId) {
        this(
                address.getStreet(),
                address.getNumber(),
                address.getZipcode(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                customerId
        );
    }

    public static List<AddressResponseDTO> convert(List<Address> addressList, String customerId) {
        return addressList.stream()
                .map(address -> new AddressResponseDTO(address, customerId))
                .toList();
    }
}
