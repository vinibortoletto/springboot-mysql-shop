package com.vinibortoletto.simpleshop.controllers;

import com.vinibortoletto.simpleshop.dtos.order.OrderRequestDTO;
import com.vinibortoletto.simpleshop.dtos.order.OrderResponseDTO;
import com.vinibortoletto.simpleshop.dtos.order.OrderStatusRequestDTO;
import com.vinibortoletto.simpleshop.models.Order;
import com.vinibortoletto.simpleshop.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "orders")
@RestController
@RequestMapping(value = "orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Operation(summary = "Finds all orders")
    @GetMapping()
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        List<Order> orderList = orderService.findAll();
        List<OrderResponseDTO> response = OrderResponseDTO.convert(orderList);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Find all orders by customer id")
    @GetMapping(value = "/customer/{customerId}")
    public ResponseEntity<List<OrderResponseDTO>> findAllByCustomerId() {
        List<Order> orderList = orderService.findAll();
        List<OrderResponseDTO> response = OrderResponseDTO.convert(orderList);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Finds an order by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(orderService.findById(id));
    }

    @Operation(summary = "Updates an order status")
    @PutMapping(value = "/status")
    public ResponseEntity<Order> updateStatus(@RequestBody @Valid OrderStatusRequestDTO dto) {
        return ResponseEntity.ok().body(orderService.updateStatus(dto));
    }

    @Operation(summary = "Create new order")
    @PostMapping()
    public ResponseEntity<OrderResponseDTO> save(@RequestBody @Valid OrderRequestDTO dto) {
        Order order = orderService.save(dto);
        OrderResponseDTO response = new OrderResponseDTO(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
