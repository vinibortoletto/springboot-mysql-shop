package com.vinibortoletto.simpleshop.fakers;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.models.Category;

import java.util.UUID;

public class CategoryFaker {
    private final Faker faker = new Faker();

    public Category createFakeCategory() {
        Category fakeCategory = new Category();

        fakeCategory.setId(String.valueOf(UUID.randomUUID()));
        fakeCategory.setName(faker.name().name());

        return fakeCategory;
    }


}
