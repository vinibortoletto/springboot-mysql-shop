package com.vinibortoletto.simpleshop.repositories;

import com.vinibortoletto.simpleshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
