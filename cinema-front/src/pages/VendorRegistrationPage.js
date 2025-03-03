import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './VendorRegistrationPage.css';

const VendorRegistrationPage = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();

    const handleRegister = async () => {
        // Validate inputs
        if (!name || !email || !password) {
            alert('Name, email, and password are required!');
            return;
        }

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            alert('Please enter a valid email address.');
            return;
        }

        setIsLoading(true); // Start loading indicator

        try {
            // Send registration request
            const response = await axios.post('http://localhost:8081/vendors/register', {
                name,
                email,
                password,
            });
            alert('Registration Successful!');
            console.log(response.data); // Log the response for debugging
            setName(''); // Clear name field
            setEmail(''); // Clear email field
            setPassword(''); // Clear password field
            navigate('/vendor/login'); // Redirect to login page
        } catch (error) {
            console.error('Error during registration:', error.response || error.message);
            if (error.response && error.response.data && error.response.data.message) {
                alert(`Registration failed: ${error.response.data.message}`);
            } else {
                alert('Registration failed. Please try again.');
            }
        } finally {
            setIsLoading(false); // Stop loading indicator
        }
    };

    return (
        <div className="vendor-registration-container">
            <h1 class="h1">Vendor Registration</h1>
            <input
                type="text"
                placeholder="Enter your name"
                value={name}
                onChange={(e) => setName(e.target.value)}
            />
            <input
                type="email"
                placeholder="Enter your email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />
            <input
                type="password"
                placeholder="Enter your password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <button onClick={handleRegister} disabled={isLoading}>
                {isLoading ? 'Registering...' : 'Register'}
            </button>
        </div>
    );
};

export default VendorRegistrationPage;