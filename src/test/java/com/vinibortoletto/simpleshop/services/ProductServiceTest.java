package com.vinibortoletto.simpleshop.services;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.models.Product;
import com.vinibortoletto.simpleshop.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {
    private final Faker faker = new Faker();

    @Mock
    private ProductRepository repository;

    @Autowired
    @InjectMocks
    private ProductService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Product createFakeProduct() {
        Product fakeProduct = new Product();

        fakeProduct.setId(String.valueOf(UUID.randomUUID()));
        fakeProduct.setName(faker.commerce().productName());
        fakeProduct.setPrice(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100)));
        fakeProduct.setStock(faker.number().numberBetween(1, 100));
        fakeProduct.setImage(faker.internet().image());
        fakeProduct.setDescription(faker.lorem().sentence());

        return fakeProduct;
    }

    @Test
    @DisplayName("should return empty array")
    void findAllCase1() {
        List<Product> expected = new ArrayList<>();
        when(repository.findAll()).thenReturn(expected);

        List<Product> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should find and return all products in database")
    void findAllCase2() {
        List<Product> expected = List.of(createFakeProduct(), createFakeProduct(), createFakeProduct());
        when(repository.findAll()).thenReturn(expected);

        List<Product> actual = service.findAll();
        assertEquals(expected, actual);
    }
}