package com.vinibortoletto.simpleshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_admin")
public class Admin implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  private String id;
  private String name;
  private String email;

  @OneToOne
  @MapsId
  @JoinColumn(name = "id")
  private User user;
}