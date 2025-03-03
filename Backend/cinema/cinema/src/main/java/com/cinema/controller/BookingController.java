package com.cinema.controller;

import com.cinema.dto.BookingRequest;
import com.cinema.dto.BookingResponse;
import com.cinema.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<BookingResponse> bookMovie(@RequestBody BookingRequest bookingRequest) {
        try {
            // Call the service to process the booking and get the response
            BookingResponse response = bookingService.bookMovie(bookingRequest);
            return ResponseEntity.ok(response);  // Send back the booking confirmation
        } catch (Exception e) {
            // Return an error response if something goes wrong
            return ResponseEntity.badRequest().body(new BookingResponse(e.getMessage(), null, null, null, null, 0));
        }
    }
}