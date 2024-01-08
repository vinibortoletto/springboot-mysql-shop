package com.vinibortoletto.simpleshop.fakers;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.dtos.user.UserRequestDTO;
import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.models.Address;
import com.vinibortoletto.simpleshop.models.Admin;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.models.User;

import java.util.UUID;

public class UserFaker {
    private final Faker faker = new Faker();

    private final AddressFaker addressFaker = new AddressFaker();

    public User createFakeUser(Role role) {
        User fakeUser = new User();

        fakeUser.setId(String.valueOf(UUID.randomUUID()));
        fakeUser.setName(faker.name().fullName());
        fakeUser.setEmail(faker.internet().emailAddress());
        fakeUser.setPassword(faker.internet().password());
        fakeUser.setRole(role);

        if (role == Role.CUSTOMER) {
            Customer customer = new Customer();
            Address address = addressFaker.createFakeAddress();

            customer.setUser(fakeUser);
            customer.setName(fakeUser.getName());
            customer.setEmail(fakeUser.getEmail());

            fakeUser.setCustomer(customer);
        } else {
            Admin admin = new Admin();

            admin.setUser(fakeUser);
            admin.setName(fakeUser.getName());
            admin.setEmail(fakeUser.getEmail());

            fakeUser.setAdmin(admin);
        }

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
