package com.vinibortoletto.simpleshop.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tb_product")
public class ProductModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private String image;
    private String description;
}
