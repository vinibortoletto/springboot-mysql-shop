package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.models.Cart;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart save(Customer customer) {
        Cart cart = new Cart();
        cart.setTotal(BigDecimal.valueOf(0.0));
        cart.setCustomer(customer);
        return cartRepository.save(cart);
    }
}
