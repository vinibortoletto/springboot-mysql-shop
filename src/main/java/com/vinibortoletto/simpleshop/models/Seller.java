package com.vinibortoletto.simpleshop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_seller")
public class Seller {
    @Id
    @OneToOne
    @JoinColumn(name = "id")
    private User user;
}

