package com.cinema.repository;

import com.cinema.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByEmail(String email);  // Query to find by email
    Optional<Vendor> findByEmailAndPassword(String email, String password);  // Query for login with email and password
}
