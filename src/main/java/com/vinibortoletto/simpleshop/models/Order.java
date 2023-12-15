package com.vinibortoletto.simpleshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinibortoletto.simpleshop.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Instant moment;
    private BigDecimal total;
    private OrderStatus status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer customer;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "id.order")
    private List<OrderProduct> products = new ArrayList<>();
}