package com.vinibortoletto.simpleshop.repositories;

import com.vinibortoletto.simpleshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    UserDetails findByName(String name);
//    UserDetails findByEmail(String email);
}
