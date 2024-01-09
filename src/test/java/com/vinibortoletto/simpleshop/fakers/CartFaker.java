package com.vinibortoletto.simpleshop.fakers;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.dtos.cart.CartRequestDTO;
import com.vinibortoletto.simpleshop.models.Cart;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.UUID;

public class CartFaker {
    private final Faker faker = new Faker();

    public Cart createFakeCart() {
        Cart fakeCart = new Cart();

        fakeCart.setId(String.valueOf(UUID.randomUUID()));
        fakeCart.setTotal(BigDecimal.valueOf(faker.number().randomDouble(2, 0, 1000)));

        return fakeCart;
    }

    public CartRequestDTO createFakeCartRequestDTO() {
        return new CartRequestDTO(
            String.valueOf(UUID.randomUUID()),
            new HashSet<>()
        );
    }
}
