package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.CartRequestDTO;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.Cart;
import com.vinibortoletto.simpleshop.models.CartProduct;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerService customerService;

    public Cart save(Customer customer) {
        Cart cart = new Cart();
        cart.setTotal(BigDecimal.valueOf(0.0));
        cart.setCustomer(customer);
        return cartRepository.save(cart);
    }

    public Cart findById(String cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        return cart.orElseThrow(() -> new NotFoundException("Cart not found"));
    }

    public void validateCustomerCart(Customer customer, Cart cart) {
        if (!Objects.equals(cart.getCustomer().getId(), customer.getId())) {
            throw new ConflictException("Cart does not belong to customer");
        }
    }

    public Cart addProductToCart(CartRequestDTO dto) {
        Customer customer = customerService.findById(dto.customerId());
        Cart cart = findById(dto.cartId());

        validateCustomerCart(customer, cart);

        for (CartProduct cartProduct : dto.cartProductSet()) {
            cart.getProducts().add(cartProduct);
        }

        return cartRepository.save(cart);
    }
}
