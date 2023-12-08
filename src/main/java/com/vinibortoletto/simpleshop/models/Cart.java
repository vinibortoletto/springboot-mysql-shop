package com.vinibortoletto.simpleshop.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_cart")
public class Cart implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Double total;

    @OneToMany(mappedBy = "id.cart")
    private Set<CartProduct> products = new HashSet<>();

//    @JsonIgnore
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User customer;
//
//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<CartItem> items;
}