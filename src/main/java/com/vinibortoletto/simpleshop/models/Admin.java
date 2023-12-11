package com.vinibortoletto.simpleshop.models;

import com.vinibortoletto.simpleshop.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "tb_admin")
public class Admin implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;
    private String name;
    private String email;
    private String password;
    private Role role;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}