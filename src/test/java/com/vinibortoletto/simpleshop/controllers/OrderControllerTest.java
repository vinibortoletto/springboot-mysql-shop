package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.order.OrderRequestDTO;
import com.vinibortoletto.simpleshop.dtos.order.OrderResponseDTO;
import com.vinibortoletto.simpleshop.fakers.AddressFaker;
import com.vinibortoletto.simpleshop.fakers.CustomerFaker;
import com.vinibortoletto.simpleshop.fakers.OrderFaker;
import com.vinibortoletto.simpleshop.models.Order;
import com.vinibortoletto.simpleshop.services.OrderService;
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

class OrderControllerTest {
    private final OrderFaker orderFaker = new OrderFaker();
    private final CustomerFaker customerFaker = new CustomerFaker();
    private final AddressFaker addressFaker = new AddressFaker();

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("findAll - should return a list of orders")
    void findAll() {
        Order order = orderFaker.createFakeOrder();
        order.setCustomer(customerFaker.createFakeCustomer());
        order.setShippingAddress(addressFaker.createFakeAddress());

        List<Order> orderList = List.of(order);
        when(orderService.findAll()).thenReturn(orderList);

        ResponseEntity<List<OrderResponseDTO>> expected = ResponseEntity
            .ok()
            .body(OrderResponseDTO.convert(orderList));

        ResponseEntity<List<OrderResponseDTO>> response = orderController.findAll();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderList.size(), Objects.requireNonNull(response.getBody()).size());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("findAllByCustomerId - should return a list of orders")
    void findAllByCustomerId() {
        Order order = orderFaker.createFakeOrder();
        order.setCustomer(customerFaker.createFakeCustomer());
        order.setShippingAddress(addressFaker.createFakeAddress());

        List<Order> orderList = List.of(order);
        when(orderService.findAll()).thenReturn(orderList);

        ResponseEntity<List<OrderResponseDTO>> expected = ResponseEntity
            .ok()
            .body(OrderResponseDTO.convert(orderList));

        ResponseEntity<List<OrderResponseDTO>> response = orderController.findAllByCustomerId();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderList.size(), Objects.requireNonNull(response.getBody()).size());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("findById - should return an order")
    void findById() {
        Order order = orderFaker.createFakeOrder();
        order.setCustomer(customerFaker.createFakeCustomer());
        order.setShippingAddress(addressFaker.createFakeAddress());

        when(orderService.findById(order.getId())).thenReturn(order);

        ResponseEntity<Order> expected = ResponseEntity
            .ok()
            .body(order);

        ResponseEntity<Order> response = orderController.findById(order.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("updateStatus - should return an order")
    void updateStatus() {
        OrderRequestDTO dto = orderFaker.createFakeOrderRequestDTO();
        Order order = orderFaker.createFakeOrder();
        BeanUtils.copyProperties(dto, order);

        order.setCustomer(customerFaker.createFakeCustomer());
        order.setShippingAddress(addressFaker.createFakeAddress());

        when(orderService.save(dto)).thenReturn(order);

        ResponseEntity<OrderResponseDTO> expected = ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new OrderResponseDTO(order));

        ResponseEntity<OrderResponseDTO> response = orderController.save(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("save - should return an order")
    void save() {
        OrderRequestDTO dto = orderFaker.createFakeOrderRequestDTO();
        Order order = orderFaker.createFakeOrder();
        BeanUtils.copyProperties(dto, order);

        order.setCustomer(customerFaker.createFakeCustomer());
        order.setShippingAddress(addressFaker.createFakeAddress());

        when(orderService.save(dto)).thenReturn(order);

        ResponseEntity<OrderResponseDTO> expected = ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new OrderResponseDTO(order));

        ResponseEntity<OrderResponseDTO> response = orderController.save(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expected, response);
    }
}