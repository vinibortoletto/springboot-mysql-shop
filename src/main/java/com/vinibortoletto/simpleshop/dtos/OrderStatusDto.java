package com.vinibortoletto.simpleshop.dtos;

import com.vinibortoletto.simpleshop.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderStatusDto(
        @NotBlank String orderId,
        @NotNull OrderStatus orderStatus
) {
}
