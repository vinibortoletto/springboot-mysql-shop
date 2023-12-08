package com.vinibortoletto.simpleshop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_admin")
public class Admin {
    @Id
    @OneToOne
    @GeneratedValue(strategy = GenerationType.UUID)
    @JoinColumn(name = "id")
    private String id;
}

