package com.cinema.service;

import com.cinema.dto.BookingRequest;
import com.cinema.dto.BookingResponse;
import com.cinema.model.Consumer;
import com.cinema.model.Movie;
import com.cinema.model.Ticket;
import com.cinema.repository.ConsumerRepository;
import com.cinema.repository.MovieRepository;
import com.cinema.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public BookingResponse bookMovie(BookingRequest bookingRequest) throws Exception {
        // Fetch the movie details
        Movie movie = movieRepository.findById(bookingRequest.getMovieId())
                .orElseThrow(() -> new Exception("Movie not found"));

        // Synchronize on the movie object to handle concurrent ticket updates
        synchronized (movie) {
            if (!movie.bookTickets(bookingRequest.getTickets())) {
                throw new Exception("Not enough tickets available for " + movie.getTitle());
            }
            movieRepository.save(movie); // Save updated ticket count
        }

        // Fetch or create the consumer
        Consumer consumer = consumerRepository.findByEmail(bookingRequest.getCustomerEmail())
                .orElseGet(() -> {
                    Consumer newConsumer = new Consumer();
                    newConsumer.setName(bookingRequest.getCustomerName());
                    newConsumer.setEmail(bookingRequest.getCustomerEmail());
                    newConsumer.setPassword(""); // Password can be set during registration
                    return consumerRepository.save(newConsumer);
                });

        // Create and save the ticket
        Ticket ticket = new Ticket();
        ticket.setConsumer(consumer);
        ticket.setMovie(movie);
        ticket.setTickets(bookingRequest.getTickets());
        ticket.setSeats(bookingRequest.getSeats()); // Optional seats information
        ticketRepository.save(ticket);

        // Prepare and return the booking response
        return new BookingResponse(
                "Booking successful!",
                bookingRequest.getCustomerName(),
                bookingRequest.getCustomerEmail(),
                movie.getTitle(),
                movie.getTime(),
                bookingRequest.getTickets()
        );
    }
}