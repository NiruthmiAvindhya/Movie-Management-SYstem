package com.cinema.service;

import com.cinema.model.Consumer;
import com.cinema.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsumerService {



    private final TicketInventory inventory;

    public ConsumerService(TicketInventory inventory) {
        this.inventory = inventory;
    }

    @Async
    public void purchaseTickets() {
        try {
            while (true) {
                if (inventory.hasTickets()) {
                    String ticket = inventory.purchaseTicket();
                    System.out.println("Customer purchased: " + ticket);
                    Thread.sleep(1000); // Simulate delay
                } else {
                    System.out.println("No tickets available.");
                    break; // Exit loop if no tickets are available
                }

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Autowired
    private ConsumerRepository consumerRepository;

    public Consumer registerConsumer(Consumer consumer) {
        // Check if email already exists
        if (consumerRepository.findByEmail(consumer.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        // Encrypt password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(consumer.getPassword());
        consumer.setPassword(encodedPassword);

        // Save consumer
        return consumerRepository.save(consumer);
    }

    public Optional<Consumer> loginConsumer(String email, String password) {
        Optional<Consumer> consumer = consumerRepository.findByEmail(email);

        if (consumer.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            // Compare entered password with the stored hashed password
            if (encoder.matches(password, consumer.get().getPassword())) {
                return consumer;
            }
        }

        return Optional.empty();
    }
}
