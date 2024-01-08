package com.vinibortoletto.simpleshop.repositories;

import com.vinibortoletto.simpleshop.models.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, String> {
    Optional<CartProduct> findByCartIdAndProductId(String cartId, String productId);
}
