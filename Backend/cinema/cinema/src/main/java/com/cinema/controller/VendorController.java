package com.cinema.controller;

import com.cinema.dto.MovieDTO;
import com.cinema.model.Movie;
import com.cinema.model.Vendor;
import com.cinema.service.MovieService;
import com.cinema.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private MovieService movieService;

    // Register Vendor
    @PostMapping("/register")
    public ResponseEntity<?> registerVendor(@RequestBody Vendor vendor) {
        try {
            Vendor registeredVendor = vendorService.registerVendor(vendor);
            return ResponseEntity.ok(registeredVendor); // Return registered vendor details
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Registration failed: " + e.getMessage());
        }
    }

    // Login Vendor
    @PostMapping("/login")
    public ResponseEntity<?> loginVendor(@RequestBody Vendor vendor) {
        try {
            // Ensure vendor ID is provided
            if (vendor.getId() == null) {
                return ResponseEntity.status(400).body("Vendor ID is required.");
            }

            // Find the vendor by vendorId
            Optional<Vendor> existingVendor = vendorService.findVendorById(vendor.getId());
            if (existingVendor.isEmpty()) {
                return ResponseEntity.status(401).body("Vendor not found with this ID.");
            }

            Vendor foundVendor = existingVendor.get();

            // Check if the email matches
            if (!foundVendor.getEmail().equals(vendor.getEmail())) {
                return ResponseEntity.status(401).body("Invalid email or password.");
            }

            // Check if the password matches
            if (!vendorService.isPasswordValid(vendor.getPassword(), foundVendor.getPassword())) {
                return ResponseEntity.status(401).body("Invalid email or password.");
            }

            // Successful login, return vendor details
            return ResponseEntity.ok(Map.of(
                    "vendorId", foundVendor.getId(),
                    "email", foundVendor.getEmail(),
                    "name", foundVendor.getName()
            ));
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            return ResponseEntity.status(500).body("Internal server error.");
        }
    }
    // Add a movie
    @PostMapping("/{vendorId}/movies")
    public ResponseEntity<?> addMovie(@PathVariable Long vendorId, @RequestBody Movie movie) {
        try {
            //  check if the vendorId matches the logged-in vendor
            Movie addedMovie = movieService.addMovie(vendorId, movie);
            return ResponseEntity.status(201).body(addedMovie); // Movie successfully added
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Failed to add movie: " + e.getMessage());
        }
    }
    @GetMapping("/{vendorId}")
    public ResponseEntity<?> getVendorById(@PathVariable Long vendorId) {
        Optional<Vendor> vendor = vendorService.findVendorById(vendorId);
        if (vendor.isPresent()) {
            return ResponseEntity.ok(vendor.get());
        } else {
            return ResponseEntity.status(404).body("Vendor not found");
        }
    }
    @GetMapping("/{vendorId}/movies")
    public List<MovieDTO> getMoviesByVendor(@PathVariable Long vendorId) {
        List<Movie> movies = movieService.getMoviesByVendor(vendorId);
        return movies.stream()
                .map(MovieDTO::new)  // Convert Movie entity to MovieDTO
                .collect(Collectors.toList());
    }


}