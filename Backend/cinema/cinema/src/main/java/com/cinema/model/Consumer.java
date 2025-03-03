package com.cinema.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    private String password;


    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Ticket> bookings;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Ticket> getBookings() {
        return bookings;
    }

    public void setBookings(List<Ticket> bookings) {
        this.bookings = bookings;
    }


}