package com.vinibortoletto.simpleshop.repositories;

import com.vinibortoletto.simpleshop.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
    Optional<Address> findByZipcodeAndNumber(String zipcode, String number);
}
