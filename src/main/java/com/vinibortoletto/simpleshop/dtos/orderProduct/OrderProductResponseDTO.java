package com.vinibortoletto.simpleshop.dtos.orderProduct;

import com.vinibortoletto.simpleshop.models.OrderProduct;
import com.vinibortoletto.simpleshop.models.Product;

import java.math.BigDecimal;
import java.util.List;

public record OrderProductResponseDTO(
        Integer quantity,
        BigDecimal subtotal,
        Product product
) {

    public OrderProductResponseDTO(OrderProduct orderProduct) {
        this(
                orderProduct.getQuantity(),
                orderProduct.getSubtotal(),
                orderProduct.getProduct()
        );
    }

    public static List<OrderProductResponseDTO> convert(List<OrderProduct> orderProductList) {
        return orderProductList.stream().map(OrderProductResponseDTO::new).toList();
    }
}
