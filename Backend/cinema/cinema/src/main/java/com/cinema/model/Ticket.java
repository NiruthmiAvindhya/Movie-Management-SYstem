package com.cinema.model;

import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerEmail;
    private String customerName;
    private int seats;  // Number of seats (tickets)
    private int tickets; // Number of tickets booked

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer; // Reference to the consumer (customer)

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie; // Reference to the movie

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getCustomerEmail() {

        return customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }



    // Getter for seats
    public int getSeats() {
        return seats;
    }

    // Setter for seats
    public void setSeats(int seats) {
        this.seats = seats;
    }
}