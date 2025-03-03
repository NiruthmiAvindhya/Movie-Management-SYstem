import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './HomePage.css'; 

const HomePage = () => {
  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(true); // To track the loading state
  const [error, setError] = useState(null); // To track errors
  // const [available_tickets, setAvailable_tickets] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchMovies = async () => {
      try {
        const response = await axios.get('http://localhost:8081/vendors/movies');
        setMovies(response.data);
      } catch (err) {
        setError('Failed to load movies. Please try again later.');
        console.error('Error fetching movies:', err);
      } finally {
        setLoading(false); //  stop loading after the request
      }
    };

    fetchMovies();
  }, []);

  return (
    <div className="homepage-container">
      <h1 class="h1">Available Movies</h1>

      {loading && <p>Loading movies...</p>} {/* Display a loading state */}
      
      {error && <p className="error-message">{error}</p>} {/* Display error message */}
      
      {!loading && !error && movies.length === 0 && (
        <p>No movies are available at the moment. Please check back later.</p>
      )}

      {!loading && !error && movies.length > 0 && (
        <div className="movie-list">
          {movies.map(movie => (
            <div key={movie.id} className="movie-card">
              <img 
                src={movie.image || "https://s.studiobinder.com/wp-content/uploads/2021/02/Best-Kids-Movies-of-All-Time-Classics-Every-Kid-Should-See-StudioBinder.jpeg"} 
                alt={`${movie.title} Poster`} 
                className="movie-image" 
              />
              <h3>{movie.title}</h3>
              <p><strong>Date</strong> {movie.description}</p>
              {/* <p><strong>Date:</strong> {movie.category}</p> */}
              <p><strong>Time:</strong> {movie.genre}</p>
              <p><strong>AvailableTickets:</strong>{movie.availableTickets}</p>
              <button 
                className="book-button" 
                onClick={() => navigate(`/book/${movie.id}`)}
              >
                Book Now
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default HomePage;