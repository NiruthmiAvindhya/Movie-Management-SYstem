
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const MovieListPage = () => {
    const [movies, setMovies] = useState([]);

    useEffect(() => {
        const fetchMovies = async () => {
            const response = await axios.get('http://localhost:8081/movies');
            setMovies(response.data);
        };
        fetchMovies();
    }, []);

    return (
        <div>
            <h1>Available Movies</h1>
            <ul>
                {movies.map((movie) => (
                    <li key={movie.id}>
                        {movie.title}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default MovieListPage;