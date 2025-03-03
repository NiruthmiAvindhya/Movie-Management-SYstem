package com.cinema.controller;


import com.cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/release")
    public ResponseEntity<String> releaseTickets(@RequestParam int ticketCount) {
        ticketService.releaseTickets(ticketCount);
        return ResponseEntity.ok("Tickets released successfully");
    }

    @PostMapping("/purchase")
    public ResponseEntity<String> purchaseTickets(@RequestParam int ticketCount) {
        boolean success = ticketService.purchaseTickets(ticketCount);
        return success ? ResponseEntity.ok("Tickets purchased successfully") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough tickets");
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelBooking(
            @RequestParam String customerEmail,
            @RequestParam Long movieId) {
        boolean success = ticketService.cancelBooking(customerEmail, movieId);

        if (success) {
            return ResponseEntity.ok("Booking canceled successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking not found or cancellation failed.");
        }
    }




}
