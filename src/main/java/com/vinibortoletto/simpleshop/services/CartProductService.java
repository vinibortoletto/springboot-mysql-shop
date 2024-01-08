package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.models.CartProduct;
import com.vinibortoletto.simpleshop.repositories.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartProductService {
    @Autowired
    private CartProductRepository cartProductRepository;

    public CartProduct save(CartProduct cartProduct) {
        Optional<CartProduct> cartProductExists = cartProductRepository.findByCartIdAndProductId(
            cartProduct.getCart().getId(),
            cartProduct.getProduct().getId()
        );

        if (cartProductExists.isPresent()) {
            throw new ConflictException("Product already exists in cart");
        }

        return cartProductRepository.save(cartProduct);
    }
}
