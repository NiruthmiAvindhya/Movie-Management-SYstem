package com.cinema.service;

import com.cinema.model.Movie;
import com.cinema.model.Vendor;
import com.cinema.repository.MovieRepository;
import com.cinema.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final VendorRepository vendorRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, VendorRepository vendorRepository) {
        this.movieRepository = movieRepository;
        this.vendorRepository = vendorRepository;
    }

    // Add movie for vendor
    public Movie addMovieForVendor(Long vendorId, Movie movie) {
        // Find the vendor by ID
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + vendorId));

        // Set the vendor for the movie
        movie.setVendor(vendor);

        // Save movie to database
        return movieRepository.save(movie);
    }

    // Get all movies for a vendor
    public List<Movie> getMoviesForVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + vendorId));

        return movieRepository.findByVendor(vendor);
    }

    public Movie addMovie(Long vendorId, Movie movie) {
        // Validate vendor
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + vendorId));

        // Associate the movie with the vendor
        movie.setVendor(vendor); // Assuming Movie has a Vendor field
        movie.setAvailableTickets(50);
        return movieRepository.save(movie); // Save movie to the database
    }
    public List<Movie> getAllMovies() {
        return movieRepository.findAll(); // Assuming you're using a repository to fetch data
    }
    public List<Movie> getMoviesByVendorId(Long vendorId) {
        return movieRepository.findByVendorId(vendorId);
    }

    public List<Movie> getMoviesByVendor(Long vendorId) {
        return movieRepository.findByVendorId(vendorId);
    }

    // Method to delete a movie
    public boolean deleteMovie(Long vendorId, Long movieId) {
        Optional<Movie> movie = movieRepository.findByIdAndVendorId(movieId, vendorId);

        if (movie.isPresent()) {
            movieRepository.delete(movie.get()); // Delete the movie if it exists
            return true;
        }

        return false; // Return false if the movie wasn't found or the vendor can't delete it
    }
}

