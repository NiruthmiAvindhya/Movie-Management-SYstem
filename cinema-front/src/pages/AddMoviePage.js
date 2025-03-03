import React, { useState } from 'react';
import axios from 'axios';
import './AddMoviePage.css';

const AddMoviePage = () => {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [genre, setGenre] = useState('');
    
    const [totalTickets, setTotalTickets] = useState('');

    const addMovie = async () => {
        const vendorId = localStorage.getItem('vendorId');
    
        try {
            const response = await axios.post(
                'http://localhost:8081/vendors/movies',
                { title, description, genre,totalTickets },
                { headers: { vendor_id: vendorId } }
            );
    
            console.log('Response:', response);
    
            if (response.status === 200) {
                alert('Movie added successfully!');
                window.location.href = '/vendor/dashboard';
            }
        } catch (error) {
            console.error('Error:', error);
    
            const errorMessage = error.response?.data || 'Failed to add movie. Please try again.';
            alert(errorMessage);
        }
    };

    return (
        <div className="add-movie-container">
            <h1>Add Movie</h1>
            <input
                type="text"
                placeholder="Title"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
            />
            <input
                type="text"
                placeholder="Date"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
            />
            <input
                type="text"
                placeholder="Time"
                value={genre}
                onChange={(e) => setGenre(e.target.value)}
            />
            <input
                type="number"
                placeholder="Total Tickets"
                value={totalTickets}
                onChange={(e) => setTotalTickets(e.target.value)}
            />
            
            <button onClick={addMovie}>Add Movie</button>
        </div>
    );
};

export default AddMoviePage;