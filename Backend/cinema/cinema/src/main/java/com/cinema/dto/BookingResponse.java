// BookingResponse.java
package com.cinema.dto;

public class BookingResponse {
    private String message;
    private String customerName;
    private String customerEmail;
    private String movieTitle;
    private String movieTime;
    private int ticketsBooked;

    // Constructor
    public BookingResponse(String message, String customerName, String customerEmail, String movieTitle, String movieTime, int ticketsBooked) {
        this.message = message;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.movieTitle = movieTitle;
        this.movieTime = movieTime;
        this.ticketsBooked = ticketsBooked;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieTime() {
        return movieTime;
    }

    public void setMovieTime(String movieTime) {
        this.movieTime = movieTime;
    }

    public int getTicketsBooked() {
        return ticketsBooked;
    }

    public void setTicketsBooked(int ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }
}