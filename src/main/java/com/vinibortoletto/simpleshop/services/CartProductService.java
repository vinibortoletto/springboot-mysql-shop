package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.models.CartProduct;
import com.vinibortoletto.simpleshop.repositories.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartProductService {
    @Autowired
    private CartProductRepository cartProductRepository;

    public CartProduct save(CartProduct cartProduct) {
        // validate if item already exists
        return cartProductRepository.save(cartProduct);
    }
}
