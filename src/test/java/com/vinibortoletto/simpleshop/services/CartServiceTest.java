package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.cart.CartRequestDTO;
import com.vinibortoletto.simpleshop.dtos.cartProduct.CartProductRequestDTO;
import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.exceptions.ConflictException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.fakers.CartFaker;
import com.vinibortoletto.simpleshop.fakers.CartProductFaker;
import com.vinibortoletto.simpleshop.fakers.ProductFaker;
import com.vinibortoletto.simpleshop.fakers.UserFaker;
import com.vinibortoletto.simpleshop.models.Cart;
import com.vinibortoletto.simpleshop.models.CartProduct;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.models.Product;
import com.vinibortoletto.simpleshop.repositories.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CartServiceTest {
    private final CartFaker cartFaker = new CartFaker();
    private final UserFaker userFaker = new UserFaker();
    private final CartProductFaker cartProductFaker = new CartProductFaker();
    private final ProductFaker productFaker = new ProductFaker();

    @Mock
    private CartProductService cartProductService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findAll - should find all carts")
    void findAllCase1() {
        List<Cart> cartList = List.of(
            cartFaker.createFakeCart(),
            cartFaker.createFakeCart()
        );

        when(cartRepository.findAll()).thenReturn(cartList);

        List<Cart> foundCartList = cartService.findAll();
        assertEquals(cartList, foundCartList);
    }

    @Test
    @DisplayName("findById - should find one cart by id")
    void findByIdCase1() {
        Cart cart = cartFaker.createFakeCart();

        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));

        Cart foundCart = cartService.findById(cart.getId());
        assertEquals(cart, foundCart);
    }

    @Test
    @DisplayName("findById - should throw NotFoundException when cart does not exist")
    void findByIdCase2() {
        Cart cart = cartFaker.createFakeCart();

        when(cartRepository.findById(cart.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> cartService.findById(cart.getId()));
    }

    @Test
    @DisplayName("findByCustomerId - should find one cart by customer id")
    void findByCustomerIdCase1() {
        Cart cart = cartFaker.createFakeCart();

        Customer customer = userFaker.createFakeUser(Role.CUSTOMER).getCustomer();
        cart.setCustomer(customer);

        String customerId = cart.getCustomer().getId();

        when(cartRepository.findByCustomerId(customerId))
            .thenReturn(Optional.of(cart));

        Cart foundCart = cartService.findByCustomerId(customerId);
        assertEquals(cart, foundCart);
    }

    @Test
    @DisplayName("findByCustomerId - should throw NotFoundException when cart does not exist")
    void findByCustomerIdCase2() {
        Cart cart = cartFaker.createFakeCart();

        Customer customer = userFaker.createFakeUser(Role.CUSTOMER).getCustomer();
        cart.setCustomer(customer);

        String customerId = cart.getCustomer().getId();

        when(cartRepository.findByCustomerId(customerId))
            .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> cartService.findByCustomerId(customerId));
    }

    @Test
    @DisplayName("save - should save cart")
    void saveCase1() {
        Customer customer = userFaker.createFakeUser(Role.CUSTOMER).getCustomer();

        Cart cart = new Cart();
        cart.setCustomer(customer);

        when(cartRepository.save(cart)).thenReturn(cart);
        Cart savedCart = cartService.save(customer);

        assertEquals(cart, savedCart);
    }

    @Test
    @DisplayName("update - should throw ConflictException when cart does not belong to customer")
    void updateCase1() {
        Cart cart = cartFaker.createFakeCart();
        Customer customer1 = userFaker.createFakeUser(Role.CUSTOMER).getCustomer();
        Customer customer2 = userFaker.createFakeUser(Role.CUSTOMER).getCustomer();

        cart.setCustomer(customer1);

        assertThrows(ConflictException.class, () -> cartService.validateCustomerCart(customer2, cart));
    }

    @Test
    @DisplayName("update - should update cart")
    void updateCase2() {
        Cart cart = cartFaker.createFakeCart();
        Customer customer = userFaker.createFakeUser(Role.CUSTOMER).getCustomer();
        cart.setCustomer(customer);

        CartRequestDTO dto = cartFaker.createFakeCartRequestDTO();
        BeanUtils.copyProperties(dto, cart);

        dto.products().add(
            cartProductFaker.createFakeCartProductRequestDTO()
        );

        when(customerService.findById(dto.customerId())).thenReturn(customer);
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        when(cartRepository.save(cart)).thenReturn(cart);

        for (CartProductRequestDTO cartProductRequestDTO : dto.products()) {
            CartProduct cartProduct = new CartProduct();
            Product product = productFaker.createFakeProduct();

            when(productService.findById(cartProductRequestDTO.id())).thenReturn(product);
            when(cartProductService.save(cartProduct)).thenReturn(cartProduct);
        }

        Cart updatedCart = cartService.update(dto, cart.getId());
        assertEquals(cart, updatedCart);
    }

    @Test
    @DisplayName("delete - should delete cart")
    void deleteCase1() {
        Cart cart = cartFaker.createFakeCart();
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        cartService.delete(cart.getId());
    }
}