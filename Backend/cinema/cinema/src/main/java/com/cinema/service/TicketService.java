package com.cinema.service;

import com.cinema.model.Movie;
import com.cinema.model.Ticket;
import com.cinema.repository.MovieRepository;
import com.cinema.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private int availableTickets=50;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MovieRepository movieRepository;

    // Updated method signature to accept the movieId and ticket object
    public Ticket bookTicket(Long movieId, Ticket ticket) {
        // Fetch the movie by its ID
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // Set the movie and other ticket details
        ticket.setMovie(movie);  // Associate the movie with the ticket
        ticket.setSeats(ticket.getSeats());  // Make sure seats are set from the request
        ticket.setCustomerName(ticket.getCustomerName());  // Set customer name
        ticket.setCustomerEmail(ticket.getCustomerEmail());  // Set customer email

        // Save the ticket to the database
        return ticketRepository.save(ticket);
    }




    // Method to release tickets ( by vendor)
    public synchronized void releaseTickets(int ticketCount) {
        availableTickets += ticketCount;
        System.out.println(ticketCount + " tickets released. Total available: " + availableTickets);
    }

    // Method to purchase tickets ( by customer)
    public synchronized boolean purchaseTickets(int ticketCount) {
        if (availableTickets >= ticketCount) {
            availableTickets -= ticketCount;
            System.out.println(ticketCount + " tickets purchased. Remaining tickets: " + availableTickets);
            return true;
        } else {
            System.out.println("Not enough tickets. Available: " + availableTickets);
            return false;
        }

    }
    public boolean cancelBooking(String customerEmail, Long movieId) {
        // Retrieve the movie by movieId
        Movie movie = movieRepository.findById(movieId).orElse(null);

        if (movie != null) {
            // Assuming that when a booking is canceled, the available tickets are incremented
            movie.setAvailableTickets(movie.getAvailableTickets() + 1);
            movieRepository.save(movie); // Save the updated movie with new available tickets
            return true; // Return true indicating successful cancellation
        }

        return false; // Return false if no movie found
    }








}

