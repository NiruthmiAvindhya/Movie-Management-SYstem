// src/services/MovieService.js

export const fetchMovies = async () => {
  try {
    const response = await fetch('/api/movies'); // Replace with your actual API endpoint
    const data = await response.json();
    return data; // Return the list of movies
  } catch (error) {
    console.error('Failed to fetch movies:', error);
    throw error; // Propagate the error for further handling
  }
};

export const addMovie = async (movieDetails) => {
  try {
    const response = await fetch('/api/movies', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(movieDetails),
    });
    if (!response.ok) {
      throw new Error('Failed to add movie');
    }
    return await response.json(); // Return the added movie data
  } catch (error) {
    console.error('Failed to add movie:', error);
    throw error; // Propagate the error for further handling
  }
};