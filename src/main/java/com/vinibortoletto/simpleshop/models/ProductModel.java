package com.vinibortoletto.simpleshop.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tb_product")
public class ProductModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private String image;
    private String description;

    public ProductModel() {
        this.id = String.valueOf(UUID.randomUUID());
    }

    public ProductModel(String name, BigDecimal price, Integer stock, String image, String description) {
        this.id = String.valueOf(UUID.randomUUID());
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.description = description;
    }
}
