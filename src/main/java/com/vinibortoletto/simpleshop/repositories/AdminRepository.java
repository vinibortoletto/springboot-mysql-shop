package com.vinibortoletto.simpleshop.repositories;

import com.vinibortoletto.simpleshop.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
}
