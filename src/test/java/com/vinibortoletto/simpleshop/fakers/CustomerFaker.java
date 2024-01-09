package com.vinibortoletto.simpleshop.fakers;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.dtos.customer.CustomerRequestDTO;
import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.models.User;

public class CustomerFaker {
    private final Faker faker = new Faker();
    private final UserFaker userFaker = new UserFaker();

    public Customer createFakeCustomer() {
        User user = userFaker.createFakeUser(Role.CUSTOMER);
        return user.getCustomer();
    }

    public CustomerRequestDTO createFakeCustomerRequestDTO() {
        return new CustomerRequestDTO(
            faker.name().fullName(),
            faker.internet().emailAddress()
        );
    }
}
