package com.vinibortoletto.simpleshop.repositories;

import com.vinibortoletto.simpleshop.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
