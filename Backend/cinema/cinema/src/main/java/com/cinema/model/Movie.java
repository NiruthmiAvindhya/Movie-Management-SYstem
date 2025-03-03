package com.cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String time;

    private String genre;  // Specify the type of the movie

    @Column(name = "available_tickets")
    private int availableTickets = 50;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    @JsonBackReference
    private Vendor vendor;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public synchronized void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public synchronized boolean bookTickets(int tickets) {
        if (availableTickets >= tickets) {
            availableTickets -= tickets;
            return true;
        }
        return false;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}




//    public int getTotalTickets() {
//        return totalTickets;
//    }
//
//    public void setTotalTickets(int totalTickets) {
//        this.totalTickets = totalTickets;
//    }
