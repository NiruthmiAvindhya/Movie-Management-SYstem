import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './VendorLoginPage.css';

const VendorLoginPage = () => {
    const [vendorId, setVendorId] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        setError('');
    
        if (!vendorId || !email || !password) {
            setError('Please enter vendor ID, email, and password.');
            return;
        }
    
        try {
            const response = await axios.post('http://localhost:8081/vendors/login', {
                id: vendorId, 
                email,
                password,
            });
    
            if (response.data.vendorId) {
                localStorage.setItem('vendorId', response.data.vendorId);
                localStorage.setItem('vendorName', response.data.name || '');
                localStorage.setItem('vendorEmail', response.data.email);
                navigate('/vendor/dashboard');
            } else {
                setError('Invalid credentials. Please try again.');
            }
        } catch (err) {
            if (err.response) {
                if (err.response.status === 401) {
                    setError('Invalid vendor ID, email, or password.');
                } else {
                    setError(`Error: ${err.response.data || 'Unable to log in.'}`);
                }
            } else {
                setError('An unexpected error occurred. Please try again later.');
            }
        }
    };

    return (
        <div className="vendor-login-container">
            <h1>Vendor Login</h1>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <div className="vendor-login-form">
                <input
                    type="text"
                    placeholder="Enter your vendor ID"
                    value={vendorId}
                    onChange={(e) => setVendorId(e.target.value)}
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
                <button onClick={handleLogin}>Login</button>
            </div>
        </div>
    );
};

export default VendorLoginPage;