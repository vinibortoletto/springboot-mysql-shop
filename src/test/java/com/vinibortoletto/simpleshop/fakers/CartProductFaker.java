package com.vinibortoletto.simpleshop.fakers;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.models.CartProduct;

public class CartProductFaker {

    private final Faker faker = new Faker();
    private final ProductFaker productFaker = new ProductFaker();
    private final CartFaker cartFaker = new CartFaker();

    public CartProduct createFakeCartProduct() {
        CartProduct cartProduct = new CartProduct();

        cartProduct.setProduct(productFaker.createFakeProduct());
//        cartProduct.setCart(cartFaker.createFakeCart());
        cartProduct.setQuantity(faker.number().numberBetween(1, 10));

        return cartProduct;
    }
}
