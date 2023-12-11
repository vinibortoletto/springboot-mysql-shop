package com.vinibortoletto.simpleshop.repositories;

import com.vinibortoletto.simpleshop.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
//    Optional<Cart> findByCustomerId(String id);
}
