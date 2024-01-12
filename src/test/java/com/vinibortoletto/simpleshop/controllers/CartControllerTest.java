package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.cart.CartRequestDTO;
import com.vinibortoletto.simpleshop.dtos.cart.CartResponseDTO;
import com.vinibortoletto.simpleshop.fakers.CartFaker;
import com.vinibortoletto.simpleshop.fakers.CustomerFaker;
import com.vinibortoletto.simpleshop.models.Cart;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CartControllerTest {
    private final CartFaker cartFaker = new CartFaker();
    private final CustomerFaker customerFaker = new CustomerFaker();
    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findAll - should return all carts")
    void findAll() {
        Cart cart = cartFaker.createFakeCart();
        Customer customer = customerFaker.createFakeCustomer();
        cart.setCustomer(customer);

        List<Cart> cartList = List.of(cart);
        when(cartService.findAll()).thenReturn(cartList);

        ResponseEntity<List<CartResponseDTO>> expected = ResponseEntity
            .ok()
            .body(CartResponseDTO.convert(cartList));

        ResponseEntity<List<CartResponseDTO>> response = cartController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartList.size(), Objects.requireNonNull(response.getBody()).size());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("findByCustomerId - should return cart by customer id")
    void findByCustomerId() {
        Cart cart = cartFaker.createFakeCart();
        Customer customer = customerFaker.createFakeCustomer();
        cart.setCustomer(customer);

        when(cartService.findByCustomerId(customer.getId())).thenReturn(cart);

        ResponseEntity<CartResponseDTO> expected = ResponseEntity
            .ok()
            .body(new CartResponseDTO(cart));

        ResponseEntity<CartResponseDTO> response = cartController.findByCustomerId(customer.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("update - should update cart")
    void update() {
        CartRequestDTO dto = cartFaker.createFakeCartRequestDTO();
        Cart cart = new Cart();
        BeanUtils.copyProperties(dto, cart);

        Customer customer = customerFaker.createFakeCustomer();
        cart.setCustomer(customer);

        when(cartService.update(dto, cart.getId())).thenReturn(cart);

        ResponseEntity<CartResponseDTO> expected = ResponseEntity
            .ok()
            .body(new CartResponseDTO(cart));

        ResponseEntity<CartResponseDTO> response = cartController.update(dto, cart.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response);
    }
}