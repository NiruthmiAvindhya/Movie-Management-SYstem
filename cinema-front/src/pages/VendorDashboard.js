import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './VendorDashboard.css';

const VendorDashboard = () => {
    const [vendorInfo, setVendorInfo] = useState({});
    const [movies, setMovies] = useState([]);
    
    useEffect(() => {
        const vendorName = localStorage.getItem('vendorName');
        const vendorEmail = localStorage.getItem('vendorEmail');
        const vendorId = localStorage.getItem('vendorId');
        
        if (vendorName && vendorEmail) {
            setVendorInfo({ name: vendorName, email: vendorEmail });
        }
        
        const fetchMovies = async () => {
            if (vendorId) {
                try {
                    const response = await axios.get(`http://localhost:8081/vendors/${vendorId}/movies`);
                    setMovies(response.data);
                } catch (error) {
                    console.error('Failed to fetch movies:', error.response || error.message);
                }
            }
        };

        fetchMovies();
    }, []);

    const handleDeleteMovie = async (movieId) => {
        const confirmDelete = window.confirm('Are you sure you want to delete this movie?');
        if (!confirmDelete) return;
    
        const vendorId = localStorage.getItem('vendorId');
        if (vendorId) {
            try {
                const response = await axios.delete(`http://localhost:8081/vendors/${vendorId}/movies/${movieId}`);
                setMovies(movies.filter((movie) => movie.id !== movieId));
                alert("Movie deleted successfully!");
            } catch (error) {
                console.error('Failed to delete movie:', error.response || error.message);
                alert("Failed to delete the movie. See console for details.");
                
                console.log(error.response ? error.response.data : error);
            }
        }
    };

    return (
        <div className="vendor-dashboard-container">
            <h1 className="h1">Vendor Dashboard</h1>
            <div className="vendor-info">
                <h2 className="h2">Vendor Information</h2>
                {vendorInfo.name ? (
                    <>
                        <p><strong>Name:</strong> {vendorInfo.name}</p>
                        <p><strong>Email:</strong> {vendorInfo.email}</p>
                    </>
                ) : (
                    <p>Loading vendor information...</p>
                )}
            </div>
            <div className="movies-list">
                <h2 className="h2">Movies Managed by You</h2>
                {movies.length === 0 ? (
                    <p>No movies added yet.</p>
                ) : (
                    <div className="movies-container">
                        {movies.map((movie) => (
                            <div key={movie.id} className="movie-item">
                                <h3>{movie.title}</h3>
                                <p>{movie.description}</p>
                                <button 
                                    className="delete-button" 
                                    onClick={() => handleDeleteMovie(movie.id)}>
                                    Delete
                                </button>
                            </div>
                        ))}
                    </div>
                )}
                <button onClick={() => window.location.href = '/vendor/add-movie'}>
                    Add New Movie
                </button>
            </div>
        </div>
    );
};

export default VendorDashboard;