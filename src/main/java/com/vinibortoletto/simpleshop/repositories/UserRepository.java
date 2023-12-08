package com.vinibortoletto.simpleshop.repositories;

import com.vinibortoletto.simpleshop.enums.Role;
import com.vinibortoletto.simpleshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    List<User> findAllByRole(Role role);
}
