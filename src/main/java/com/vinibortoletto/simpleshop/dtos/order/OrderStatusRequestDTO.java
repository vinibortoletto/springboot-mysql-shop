package com.vinibortoletto.simpleshop.dtos.order;

import com.vinibortoletto.simpleshop.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderStatusRequestDTO(
        @NotBlank String orderId,
        @NotNull OrderStatus orderStatus
) {
}
