package com.vinibortoletto.simpleshop.repositories;

import com.vinibortoletto.simpleshop.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, String> {
    Optional<ProductModel> findByName(String name);
}
