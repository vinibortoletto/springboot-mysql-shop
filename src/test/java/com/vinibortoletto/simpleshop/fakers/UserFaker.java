package com.vinibortoletto.simpleshop.fakers;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.dtos.user.UserRequestDTO;
import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.models.User;

import java.util.UUID;

public class UserFaker {
    private final Faker faker = new Faker();

    public User createFakeUser(Role role) {
        User fakeUser = new User();

        fakeUser.setId(String.valueOf(UUID.randomUUID()));
        fakeUser.setName(faker.name().fullName());
        fakeUser.setEmail(faker.internet().emailAddress());
        fakeUser.setPassword(faker.internet().password());
        fakeUser.setRole(role);

        return fakeUser;
    }

    public UserRequestDTO createFakeUserRequestDTO(Role role) {
        return new UserRequestDTO(
            faker.name().fullName(),
            faker.internet().emailAddress(),
            faker.internet().password(),
            role
        );
    }
}
