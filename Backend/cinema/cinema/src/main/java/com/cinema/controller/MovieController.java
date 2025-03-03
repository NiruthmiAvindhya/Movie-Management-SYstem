package com.cinema.controller;

import com.cinema.model.Movie;
import com.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendors")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // Endpoint to add a movie
    @PostMapping("/movies")
    public ResponseEntity<?> addMovie(@RequestHeader("vendor_id") Long vendorId, @RequestBody Movie movie) {
        try {
            Movie addedMovie = movieService.addMovie(vendorId, movie);
            System.out.println("Movie added successfully: " + addedMovie);

            // Add "Connection: close" header to ensure the connection is properly closed
            return ResponseEntity.ok()
                    .header("Connection", "close")
                    .body(addedMovie);
        } catch (Exception e) {
            System.err.println("Error while adding movie: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Connection", "close") // Add this here as well
                    .body("Error adding movie");
        }
    }

    //    // Endpoint to get all movies for a vendor
//    @GetMapping("/{vendorId}/movies")
//    public ResponseEntity<List<Movie>> getMovies(@PathVariable Long vendorId) {
//        try {
//            System.out.println("Fetching movies for vendor ID: " + vendorId);
//            List<Movie> movies = movieService.getMoviesForVendor(vendorId);
//
//            if (movies != null && !movies.isEmpty()) {
//                System.out.println("Movies found for vendor ID: " + vendorId);
//                return new ResponseEntity<>(movies, HttpStatus.OK);
//            } else {
//                System.out.println("No movies found for vendor ID: " + vendorId);
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            System.err.println("Error while fetching movies for vendor ID: " + vendorId + ". Error: " + e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    // Endpoint to get all movies
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies(); // Get all movies from the service
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build(); // No movies available
        }
        return ResponseEntity.ok(movies); // Return movies list
    }


    // DELETE request to delete a movie
    @DeleteMapping("/{vendorId}/movies/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long vendorId, @PathVariable Long movieId) {
        // Try to delete the movie by movieId and vendorId
        boolean isDeleted = movieService.deleteMovie(vendorId, movieId);

        if (!isDeleted) {
            // If the movie is not found or the vendor doesn't have permission to delete it, return 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found or you do not have permission to delete it.");
        }

        // Return a success response
        return ResponseEntity.status(HttpStatus.OK).body("Movie deleted successfully.");
    }

}


