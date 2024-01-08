package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.fakers.CartProductFaker;
import com.vinibortoletto.simpleshop.models.CartProduct;
import com.vinibortoletto.simpleshop.repositories.CartProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CartProductServiceTest {
    private final CartProductFaker cartProductFaker = new CartProductFaker();

    @Mock
    private CartProductRepository cartProductRepository;

    @InjectMocks
    private CartProductService cartProductService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("save - should throw exception if product already exists in cart")
    void saveCase1() {
        CartProduct cartProduct = cartProductFaker.createFakeCartProduct();
        String cartId = cartProduct.getCart().getId();
        String productId = cartProduct.getProduct().getId();

        when(cartProductRepository.findByCartIdAndProductId(cartId, productId))
            .thenReturn(Optional.of(cartProduct));

        assertThrows(ConflictException.class, () -> cartProductService.save(cartProduct));
        verify(cartProductRepository).findByCartIdAndProductId(cartId, productId);
    }

    @Test
    @DisplayName("save - should save a product in cart")
    void saveCase2() {
        CartProduct cartProduct = cartProductFaker.createFakeCartProduct();
        String cartId = cartProduct.getCart().getId();
        String productId = cartProduct.getProduct().getId();

        when(cartProductRepository.findByCartIdAndProductId(cartId, productId)).thenReturn(Optional.empty());
        when(cartProductRepository.save(cartProduct)).thenReturn(cartProduct);

        CartProduct savedCartProduct = cartProductService.save(cartProduct);

        assertEquals(cartProduct, savedCartProduct);
        verify(cartProductRepository).findByCartIdAndProductId(cartId, productId);
        verify(cartProductRepository).save(cartProduct);
    }
}