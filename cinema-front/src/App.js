// src/App.js

import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './styles/global.css'; // Import global CSS
import Layout from './components/Layout'; // Layout component to wrap around pages

// Pages 
import HomePage from './pages/HomePage';
import VendorLoginPage from './pages/VendorLoginPage';
import VendorDashboard from './pages/VendorDashboard';
import AddMoviePage from './pages/AddMoviePage';
import VendorRegistrationPage from './pages/VendorRegistrationPage';
import BookingMovie from './pages/BookingMovie';
import BookMovie from './pages/BookMovie';
// App Component
function App() {
  return (
    <Router>
      <div className="App">
        {/* ----*/}
        <Layout>
          <Routes>
            {/* ---- */}
            <Route path="/" element={<HomePage />} />
            <Route path="/vendor/login" element={<VendorLoginPage />} />
            <Route path="/vendor/dashboard" element={<VendorDashboard />} />
            <Route path="/vendor/add-movie" element={<AddMoviePage />} />
            <Route path="/vendor/register" element={<VendorRegistrationPage />} />
            <Route path="/vendor/add-movie" element={<AddMoviePage />} />
            <Route path="/book/:movieId" element={<BookingMovie />} />
            <Route path="/book/:movieId" element={<BookMovie />} />
            
            {/* --- */}
          </Routes>
        </Layout>
      </div>
    </Router>
  );
}

export default App;