package com.vinibortoletto.simpleshop.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tb_customer")
public class Customer extends User {
    @ManyToOne
    @JoinColumn(name = "address_id")
    protected Set<Address> addresses;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;
}

