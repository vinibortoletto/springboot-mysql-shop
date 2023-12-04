package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.models.Order;
import com.vinibortoletto.simpleshop.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "orders")
@RestController
@RequestMapping(value = "orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Operation(summary = "Finds all orders")
    @GetMapping()
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok().body(orderService.findAll());
    }

    @Operation(summary = "Finds an order by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(orderService.findById(id));
    }
}
