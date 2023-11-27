package com.vinibortoletto.simpleshop.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_address")
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String street;
    private Integer number;
    @Column(name = "zip_code")
    private Integer zipCode;
    private String neighborhood;
    private String city;
    private String state;
    private String country;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
}