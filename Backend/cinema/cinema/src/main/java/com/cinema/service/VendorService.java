package com.cinema.service;

import com.cinema.model.Vendor;
import com.cinema.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendorService {


    private final TicketInventory inventory;

    public VendorService(TicketInventory inventory) {
        this.inventory = inventory;
    }

    @Async
    public void releaseTickets() {
        try {
            for (int i = 1; i <= 10; i++) {
                String ticket = "Ticket-" + i;
                inventory.addTicket(ticket);
                System.out.println("Vendor released: " + ticket);
                Thread.sleep(500); // Simulate delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Use the PasswordEncoder bean

    // Register a new vendor
    public Vendor registerVendor(Vendor vendor) {
        // Check if the email already exists in the database
        if (vendorRepository.findByEmail(vendor.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        // Hash the password before saving
        vendor.setPassword(passwordEncoder.encode(vendor.getPassword()));

        // Save the vendor and return the saved object
        return vendorRepository.save(vendor);
    }

    // Login a vendor by validating their email and password
    public Optional<Vendor> loginVendor(String email, String password) {
        // Find the vendor by email
        Optional<Vendor> vendor = vendorRepository.findByEmail(email);

        // Validate the vendor and the password
        if (vendor.isPresent() && passwordEncoder.matches(password, vendor.get().getPassword())) {
            return vendor;
        }

        // Return empty if authentication fails
        return Optional.empty();
    }

    // Find a vendor by email (for modular usage in the controller)
    public Optional<Vendor> findByEmail(String email) {
        return vendorRepository.findByEmail(email);
    }

    // Validate the password (for modular usage in the controller)
    public boolean isPasswordValid(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public Optional<Vendor> findVendorById(Long vendorId) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return vendorRepository.findById(vendorId);
    }

}