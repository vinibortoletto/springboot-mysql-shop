package com.vinibortoletto.simpleshop.services;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.dtos.ProductDto;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.Product;
import com.vinibortoletto.simpleshop.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

    private ProductDto createFakeProductDto() {
        return new ProductDto(
                faker.commerce().productName(),
                BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100)),
                faker.number().numberBetween(1, 100),
                faker.internet().image(),
                faker.lorem().sentence()
        );
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
    @DisplayName("should find all products")
    void findAllCase2() {
        List<Product> expected = List.of(createFakeProduct(), createFakeProduct(), createFakeProduct());
        when(repository.findAll()).thenReturn(expected);

        List<Product> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should find one product by id")
    void findByIdCase1() {
        Product expected = createFakeProduct();
        when(repository.findById(expected.getId())).thenReturn(Optional.of(expected));
        Product actual = service.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should throw exception if product is not found")
    void findByIdCase2() {
        Product expected = createFakeProduct();
        when(repository.findById(expected.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.findById(expected.getId()));
    }

    @Test
    @DisplayName("should throw exception if product already exists")
    void saveCase1() {
        String expectedMessage = "Product already exists in database";
        ProductDto dto = createFakeProductDto();
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);

        when(repository.findByName(dto.name())).thenReturn(Optional.of(product));

        ConflictException exception = assertThrows(
                ConflictException.class,
                () -> service.save(dto)
        );

        assertEquals(expectedMessage, exception.getMessage());
        verify(repository, times(1)).findByName(dto.name());
        verify(repository, never()).save(product);
    }

    @Test
    @DisplayName("should save a product")
    void saveCase2() {
        ProductDto dto = createFakeProductDto();
        Product expected = new Product();
        BeanUtils.copyProperties(dto, expected);

        when(repository.findByName(dto.name())).thenReturn(Optional.empty());
        when(repository.save(expected)).thenReturn(expected);

        Product actual = service.save(dto);
        verify(repository, times(1)).findByName(dto.name());
        assertEquals(expected, actual);
    }
}