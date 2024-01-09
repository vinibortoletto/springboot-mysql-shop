package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.order.OrderRequestDTO;
import com.vinibortoletto.simpleshop.dtos.order.OrderStatusRequestDTO;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.fakers.*;
import com.vinibortoletto.simpleshop.models.Address;
import com.vinibortoletto.simpleshop.models.Cart;
import com.vinibortoletto.simpleshop.models.Customer;
import com.vinibortoletto.simpleshop.models.Order;
import com.vinibortoletto.simpleshop.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private final OrderFaker orderFaker = new OrderFaker();
    private final CustomerFaker customerFaker = new CustomerFaker();
    private final AddressFaker addressFaker = new AddressFaker();
    private final CartFaker cartFaker = new CartFaker();
    private final CartProductFaker cartProductFaker = new CartProductFaker();

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private CartService cartService;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("findAll - should find all orders")
    void findAllCase1() {
        List<Order> orderList = List.of(
            orderFaker.createFakeOrder(),
            orderFaker.createFakeOrder()
        );

        when(orderRepository.findAll()).thenReturn(orderList);

        List<Order> foundOrderList = orderService.findAll();
        assertEquals(orderList, foundOrderList);
    }

    @Test
    @DisplayName("findAllByCustomerId - should find all orders by customer id")
    void findAllByCustomerIdCase1() {
        List<Order> orderList = List.of(
            orderFaker.createFakeOrder(),
            orderFaker.createFakeOrder()
        );
        String customerId = String.valueOf(UUID.randomUUID());

        when(orderRepository.findAllByCustomerId(customerId)).thenReturn(orderList);

        List<Order> foundOrderList = orderService.findAllByCustomerId(customerId);
        assertEquals(orderList, foundOrderList);
    }

    @Test
    @DisplayName("findById - should find one order by id")
    void findByIdCase1() {
        Order order = orderFaker.createFakeOrder();
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        Order foundOrder = orderService.findById(order.getId());
        assertEquals(order, foundOrder);
    }

    @Test
    @DisplayName("findById - should throw exception if order was not found")
    void findByIdCase2() {
        Order order = orderFaker.createFakeOrder();
        when(orderRepository.findById(order.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> orderService.findById(order.getId()));
    }

    @Test
    @DisplayName("updateStatus - should update order status")
    void updateStatusCase1() {
        Order order = orderFaker.createFakeOrder();
        OrderStatusRequestDTO dto = new OrderStatusRequestDTO(
            order.getId(),
            order.getStatus()
        );

        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order updatedOrder = orderService.updateStatus(dto);
        assertEquals(dto.orderStatus(), updatedOrder.getStatus());
    }

    @Test
    @DisplayName("save - should save an order")
    void saveCase1() {
        Cart cart = cartFaker.createFakeCart();
        Customer customer = customerFaker.createFakeCustomer();
        Address address = addressFaker.createFakeAddress();
        Order order = orderFaker.createFakeOrder();

        OrderRequestDTO dto = orderFaker.createFakeOrderRequestDTO();
        BeanUtils.copyProperties(dto, order);

        cart.getProducts().add(
            cartProductFaker.createFakeCartProduct()
        );

        when(cartService.findByCustomerId(dto.customerId())).thenReturn(cart);
        when(addressService.findById(address.getId())).thenReturn(address);
        doNothing().when(cartService).delete(cart.getId());
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderService.save(dto);
        assertEquals(order, savedOrder);
    }

}