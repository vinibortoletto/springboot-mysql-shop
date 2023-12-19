package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.OrderRequestDTO;
import com.vinibortoletto.simpleshop.dtos.OrderStatusRequestDTO;
import com.vinibortoletto.simpleshop.enums.OrderStatus;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.*;
import com.vinibortoletto.simpleshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressService addressService;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new NotFoundException("Order not found"));
    }

    public Order updateStatus(OrderStatusRequestDTO dto) {
        Order order = findById(dto.orderId());
        order.setStatus(dto.orderStatus());
        return orderRepository.save(order);
    }

    public Order save(OrderRequestDTO dto) {
        Order order = new Order();
        Cart cart = cartService.findByCustomerId(dto.customerId());
        Address shippingAddress = addressService.findById(dto.shippingAddressId());

        order.setStatus(OrderStatus.AWAITING_PAYMENT);
        order.setMoment(new Date().toInstant());
        order.setTotal(cart.getTotal());
        order.setCustomer(cart.getCustomer());
        order.setShippingAddress(shippingAddress);

        for (CartProduct cartProduct : cart.getProducts()) {
            OrderProduct orderProduct = new OrderProduct();

            orderProduct.setProduct(cartProduct.getProduct());
            orderProduct.setOrder(order);
            orderProduct.setQuantity(cartProduct.getQuantity());
            orderProduct.setSubtotal(cartProduct.getSubtotal());

            order.getProducts().add(orderProduct);
        }

        cartService.delete(cart.getId());
        return orderRepository.save(order);
    }
}