package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.cart.CartRequestDTO;
import com.vinibortoletto.simpleshop.dtos.cart.CartResponseDTO;
import com.vinibortoletto.simpleshop.models.Cart;
import com.vinibortoletto.simpleshop.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "carts")
@RestController
@RequestMapping(value = "/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @Operation(summary = "Find all carts")
    @GetMapping()
    public ResponseEntity<List<CartResponseDTO>> findAll() {
        List<Cart> cartList = cartService.findAll();
        List<CartResponseDTO> response = CartResponseDTO.convert(cartList);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Find cart by customer id")
    @GetMapping(value = "/customer/{customerId}")
    public ResponseEntity<CartResponseDTO> findByCustomerId(@PathVariable String customerId) {
        Cart cart = cartService.findByCustomerId(customerId);
        CartResponseDTO response = new CartResponseDTO(cart);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Add product to cart")
    @PutMapping(value = "/{cartId}")
    public ResponseEntity<CartResponseDTO> update(
        @RequestBody @Valid CartRequestDTO dto, @PathVariable String cartId
    ) {
        Cart cart = cartService.update(dto, cartId);
        CartResponseDTO response = new CartResponseDTO(cart);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
