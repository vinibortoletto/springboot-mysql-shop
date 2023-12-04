package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.Order;
import com.vinibortoletto.simpleshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new NotFoundException("Order not found"));
    }
}