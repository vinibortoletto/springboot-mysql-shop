package com.vinibortoletto.simpleshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinibortoletto.simpleshop.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "tb_user")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private final List<Address> address = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private Role role;
}
