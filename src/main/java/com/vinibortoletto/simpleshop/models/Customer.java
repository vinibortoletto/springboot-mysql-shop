package com.vinibortoletto.simpleshop.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tb_customer")
public class Customer {
    @Id
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "tb_customer_address",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> addresses = new HashSet<>();

    @OneToOne(mappedBy = "customer")
    private Cart cart;
}

