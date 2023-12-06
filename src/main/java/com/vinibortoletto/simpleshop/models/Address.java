package com.vinibortoletto.simpleshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

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
    private String number;
    private String zipcode;
    private String neighborhood;
    private String city;
    private String state;
    private String country;

    @JsonIgnore
    @ManyToMany(mappedBy = "addresses")
    private Set<Customer> customers;

    @JsonIgnore
    @OneToMany(mappedBy = "shippingAddress")
    private Set<Order> orders;
}

