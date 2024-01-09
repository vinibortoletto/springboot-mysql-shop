package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.cart.CartRequestDTO;
import com.vinibortoletto.simpleshop.dtos.cartProduct.CartProductRequestDTO;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.Cart;
import com.vinibortoletto.simpleshop.models.CartProduct;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.models.Product;
import com.vinibortoletto.simpleshop.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private ProductService productService;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart findById(String cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        return cart.orElseThrow(() -> new NotFoundException("Cart not found"));
    }

    public Cart findByCustomerId(String customerId) {
        Optional<Cart> cart = cartRepository.findByCustomerId(customerId);
        return cart.orElseThrow(() -> new NotFoundException("Cart not found"));
    }

    public Cart save(Customer customer) {
        Cart cart = new Cart();
        cart.setCustomer(customer);
        return cartRepository.save(cart);
    }

    public void validateCustomerCart(Customer customer, Cart cart) {
        if (!customer.equals(cart.getCustomer())) {
            throw new ConflictException("Cart does not belong to customer");
        }
    }

    public Cart update(CartRequestDTO dto, String cartId) {
        Customer customer = customerService.findById(dto.customerId());
        Cart cart = findById(cartId);

        validateCustomerCart(customer, cart);

        for (CartProductRequestDTO cartProductRequestDTO : dto.products()) {
            CartProduct cartProduct = new CartProduct();
            Product product = productService.findById(cartProductRequestDTO.id());

            cartProduct.setCart(cart);
            cartProduct.setProduct(product);
            cartProduct.setQuantity(cartProductRequestDTO.quantity());
            cartProduct.setSubtotal(cartProduct.getSubtotal());
            cartProductService.save(cartProduct);

            cart.getProducts().add(cartProduct);
        }

        cart.setTotal(cart.getTotal());

        return cartRepository.save(cart);
    }

    public void delete(String cartId) {
        Cart cart = findById(cartId);
        cartRepository.delete(cart);
    }
}
