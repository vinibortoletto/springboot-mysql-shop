package com.vinibortoletto.simpleshop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_admin")
public class Admin {
    @Id
    @OneToOne
    @JoinColumn(name = "id")
    private User user;
}

