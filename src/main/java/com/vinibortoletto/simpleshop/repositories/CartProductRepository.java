package com.vinibortoletto.simpleshop.repositories;

import com.vinibortoletto.simpleshop.models.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, String> {
}
