package com.vinibortoletto.simpleshop.dtos;

import com.vinibortoletto.simpleshop.models.Address;

import java.util.List;

public record AddressResponseDTO(
        String id,
        String street,
        String number,
        String zipcode,
        String neighborhood,
        String city,
        String state,
        String country
) {
    public AddressResponseDTO(Address address) {
        this(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getZipcode(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getCountry()
        );
    }

    public static List<AddressResponseDTO> convert(List<Address> addressList) {
        return addressList.stream().map(AddressResponseDTO::new).toList();
    }
}
