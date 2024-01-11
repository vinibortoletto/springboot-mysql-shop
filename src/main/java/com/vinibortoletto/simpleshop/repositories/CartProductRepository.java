package com.vinibortoletto.simpleshop.repositories;

import com.vinibortoletto.simpleshop.models.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, String> {
    @Query(value = "SELECT cp FROM cart_product WHERE cp.cart_id = :cartId AND cp.product_id = :productId", nativeQuery = true)
    Optional<CartProduct> findByCartIdAndProductId(@Param("cartId") String cartId, @Param("productId") String productId);
}
