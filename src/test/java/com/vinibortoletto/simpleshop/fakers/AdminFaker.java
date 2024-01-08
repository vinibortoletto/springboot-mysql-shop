package com.vinibortoletto.simpleshop.fakers;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.dtos.admin.AdminRequestDTO;
import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.models.Admin;
import com.vinibortoletto.simpleshop.models.User;

public class AdminFaker {
    private final Faker faker = new Faker();
    private final UserFaker userFaker = new UserFaker();

    public Admin createFakeAdmin() {
        User user = userFaker.createFakeUser(Role.ADMIN);
        return user.getAdmin();
    }

    public AdminRequestDTO createFakeAdminRequestDTO() {
        return new AdminRequestDTO(
            faker.name().fullName(),
            faker.internet().emailAddress()
        );
    }
}
