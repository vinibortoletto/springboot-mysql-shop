package com.vinibortoletto.simpleshop.dtos;

import com.vinibortoletto.simpleshop.enums.OrderStatus;
import com.vinibortoletto.simpleshop.models.Order;
import com.vinibortoletto.simpleshop.utils.CustomDateTimeFormatter;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponseDTO(
        String id,
        String moment,
        BigDecimal total,
        OrderStatus status,
        String customerId,
        String shippingAddressId,
        List<OrderProductResponseDTO> products
) {


    public OrderResponseDTO(Order order) {
        this(
                order.getId(),
                CustomDateTimeFormatter.formatDateTime(order.getMoment()),
                order.getTotal(),
                order.getStatus(),
                order.getCustomer().getId(),
                order.getShippingAddress().getId(),
                OrderProductResponseDTO.convert(order.getProducts())
        );
    }

    public static List<OrderResponseDTO> convert(List<Order> orderList) {
        return orderList.stream().map(OrderResponseDTO::new).toList();
    }
}
