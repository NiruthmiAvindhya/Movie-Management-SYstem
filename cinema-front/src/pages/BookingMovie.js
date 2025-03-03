import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import './BookingMovie.css';

const BookMovie = () => {
  const { movieId } = useParams(); // Get the movie ID from the URL
  const [customerName, setCustomerName] = useState('');
  const [customerEmail, setCustomerEmail] = useState('');
  const [tickets, setTickets] = useState(1);
  const [success, setSuccess] = useState('');
  const [error, setError] = useState('');
  const [ticketDetails, setTicketDetails] = useState(null); // State to hold ticket details

  const handleBooking = async () => {
    try {
      const response = await axios.post('http://localhost:8081/customers/book', {
        customerName,
        customerEmail,
        movieId,
        tickets,
      });
  
      // Update state with success message and ticket details
      setSuccess(response.data.message || 'Booking successful!');
      setError('');
      setTicketDetails({
        customerName: response.data.customerName,
        customerEmail: response.data.customerEmail,
        movieTitle: response.data.movieTitle,
        tickets: response.data.tickets,
      });
    } catch (err) {
      setError(err.response?.data?.message || 'Booking failed. Please try again.');
      setSuccess('');
      setTicketDetails(null); // Clear ticket details on error
    }
  };

  const cancelBooking = async () => {
    try {
      // Assuming the backend requires customerEmail and bookingId for cancellation
      const response = await axios.post('http://localhost:8081/tickets/cancel', {
        customerEmail,  // Send the customer's email to identify the booking
        bookingId: ticketDetails.bookingId,  // Send the bookingId to identify the booking
      });

      setSuccess('Booking cancelled successfully!');
      setError('');
      setTicketDetails(null); // Clear ticket details on successful cancellation
    } catch (err) {
      setError(err.response?.data?.message || 'Cancellation failed. Please try again.');
      setSuccess('');
    }
  };

  return (
    <div>
      <h1 className="h1">Book Movie</h1>
      <p className="h2">Movie ID: {movieId}</p>
      <form onSubmit={(e) => e.preventDefault()}>
        <div>
          <label className="h2">Name:</label>
          <input
            className="input"
            type="text"
            value={customerName}
            onChange={(e) => setCustomerName(e.target.value)}
            required
          />
        </div>
        <div>
          <label className="h2">Email:</label>
          <input
            className="input"
            type="email"
            value={customerEmail}
            onChange={(e) => setCustomerEmail(e.target.value)}
            required
          />
        </div>
        <div>
          <label className="h2">Tickets:</label>
          <input
            className="input"
            id="h2"
            type="number"
            value={tickets}
            min="1"
            onChange={(e) => setTickets(Number(e.target.value))}
            required
          />
        </div>
        <button onClick={handleBooking} className="btn">
          Confirm Booking
        </button>
      </form>

      {/* Display Success or Error Message */}
      {success && <p style={{ color: 'green' }}>{success}</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}

      {/* Display Ticket Details */}
      {ticketDetails && (
        <div
          style={{
            marginTop: '20px',
            border: '6px solid #ccc',
            padding: '10px',
            color: 'black',
            marginLeft: '50px',
            width: '1200px',
            background: 'skyblue',
          }}
        >
          <h2 className="h3">Booking Ticket</h2>
          <p>
            <strong>Name:</strong> {ticketDetails.customerName}
          </p>
          <p>
            <strong>Email:</strong> {ticketDetails.customerEmail}
          </p>
          <p>
            <strong>Movie Name:</strong> {ticketDetails.movieTitle}
          </p>
          <p>
            <strong>Tickets Booked:</strong> {ticketDetails.tickets}
          {tickets}</p>

          {/* <p>
          <strong>Tickets Booked:</strong> 
         </p> */}
          {/* Dynamically display tickets from the input */}
          <button onClick={cancelBooking} className="btn" style={{ background: 'red', color: 'white' }}>
            Cancel Booking
          </button>
        </div>
      )}
    </div>
  );
};

export default BookMovie;