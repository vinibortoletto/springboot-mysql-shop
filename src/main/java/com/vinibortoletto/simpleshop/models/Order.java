package com.vinibortoletto.simpleshop.models;

import com.vinibortoletto.simpleshop.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

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

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_price")
    private Double totalPrice;
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @OneToOne
    @JoinColumn(name = "shipping_address_id")
    private Address address;
}
