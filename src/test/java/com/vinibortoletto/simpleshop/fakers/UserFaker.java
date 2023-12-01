package com.vinibortoletto.simpleshop.fakers;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.dtos.UserDto;
import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.models.User;

import java.util.ArrayList;
import java.util.UUID;

public class UserFaker {
    private final Faker faker = new Faker();

    public User createFakeUser() {
        User fakeUser = new User();

        fakeUser.setId(String.valueOf(UUID.randomUUID()));
        fakeUser.setName(faker.name().fullName());
        fakeUser.setEmail(faker.internet().emailAddress());
        fakeUser.setPhone(faker.phoneNumber().phoneNumber());
        fakeUser.setPassword(faker.internet().password());
        fakeUser.setRole(Role.SELLER);

        return fakeUser;
    }

    public UserDto createFakeUserDto() {
        return new UserDto(
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.phoneNumber().phoneNumber(),
                new ArrayList<>(),
                faker.internet().password(),
                Role.SELLER
        );
    }
}
