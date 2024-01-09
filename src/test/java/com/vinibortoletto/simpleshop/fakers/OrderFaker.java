package com.vinibortoletto.simpleshop.fakers;

import com.github.javafaker.Faker;
import com.vinibortoletto.simpleshop.dtos.order.OrderRequestDTO;
import com.vinibortoletto.simpleshop.enums.OrderStatus;
import com.vinibortoletto.simpleshop.models.Order;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderFaker {
    private final Faker faker = new Faker();


    public Order createFakeOrder() {
        Order order = new Order();

        order.setId(String.valueOf(UUID.randomUUID()));
        order.setMoment(faker.date().birthday().toInstant());
        order.setTotal(BigDecimal.valueOf(faker.number().randomDouble(2, 0, 1000)));
        order.setStatus(OrderStatus.AWAITING_PAYMENT);

        return order;
    }

    public OrderRequestDTO createFakeOrderRequestDTO() {
        return new OrderRequestDTO(
            String.valueOf(UUID.randomUUID()),
            String.valueOf(UUID.randomUUID())
        );
    }
}
