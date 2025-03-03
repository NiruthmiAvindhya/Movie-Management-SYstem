package com.cinema.repository;

import com.cinema.model.Movie;
import com.cinema.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByVendor(Vendor vendor);
    List<Movie> findByVendorId(@Param("vendorId") Long vendorId);
    Optional<Movie> findByIdAndVendorId(Long movieId, Long vendorId);
}