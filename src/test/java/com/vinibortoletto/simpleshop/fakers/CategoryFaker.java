package com.vinibortoletto.simpleshop.fakers;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.dtos.category.CategoryRequestDTO;
import com.vinibortoletto.simpleshop.models.Category;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CategoryFaker {
    private final Faker faker = new Faker();

    public Category createFakeCategory() {
        Category fakeCategory = new Category();

        fakeCategory.setId(String.valueOf(UUID.randomUUID()));
        fakeCategory.setName(faker.name().name());

        return fakeCategory;
    }

    public CategoryRequestDTO createFakeCategoryRequestDTO() {
        List<String> categories = Arrays.asList(String.valueOf(UUID.randomUUID()), String.valueOf(UUID.randomUUID()));

        return new CategoryRequestDTO(
            "Shoes"
        );
    }
}
