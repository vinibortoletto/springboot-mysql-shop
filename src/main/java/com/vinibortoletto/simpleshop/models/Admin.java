package com.vinibortoletto.simpleshop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_admin")
public class Admin extends User {
}

