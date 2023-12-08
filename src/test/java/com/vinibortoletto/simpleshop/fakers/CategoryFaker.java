package com.vinibortoletto.simpleshop.fakers;

import com.github.javafaker.Faker;

import java.util.UUID;

public class CategoryFaker {
    private final Faker faker = new Faker();

    public _Category createFakeCategory() {
        _Category fakeCategory = new _Category();

        fakeCategory.setId(String.valueOf(UUID.randomUUID()));
        fakeCategory.setName(faker.name().name());

        return fakeCategory;
    }


}
