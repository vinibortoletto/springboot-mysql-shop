package com.vinibortoletto.simpleshop.fakers;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.models.Address;

import java.util.UUID;

public class AddressFaker {
    private final Faker faker = new Faker();

    public Address createFakeAddress() {
        Address fakeAddress = new Address();

        fakeAddress.setId(String.valueOf(UUID.randomUUID()));
        fakeAddress.setStreet(faker.address().streetAddress());
        fakeAddress.setNumber(faker.number().digit());
        fakeAddress.setZipcode(faker.address().zipCode());
        fakeAddress.setNeighborhood(faker.name().fullName());
        fakeAddress.setCity(faker.address().city());
        fakeAddress.setState(faker.address().state());
        fakeAddress.setCountry(faker.address().country());

        return fakeAddress;
    }

//    public AddressDto createFakeAddressDto() {
//        return new AddressDto(
//                faker.address().streetAddress(),
//                faker.number().digit(),
//                faker.address().zipCode(),
//                faker.name().fullName(),
//                faker.address().city(),
//                faker.address().state(),
//                faker.address().country()
//        );
//    }
}
