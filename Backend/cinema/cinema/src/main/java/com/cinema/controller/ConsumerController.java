package com.cinema.controller;

import com.cinema.model.Movie;
import com.cinema.model.Ticket;
import com.cinema.service.MovieService;
import com.cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class ConsumerController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private TicketService ticketService;

    // Fetch all available movies
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movies);
    }

    // Book a ticket for a specific movie
    @PostMapping("/book/{movieId}")
    public ResponseEntity<?> bookTicket(
            @PathVariable Long movieId,
            @RequestBody Ticket ticketDetails
    ) {
        try {
            Ticket bookedTicket = ticketService.bookTicket(movieId, ticketDetails);
            return ResponseEntity.ok(bookedTicket);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}