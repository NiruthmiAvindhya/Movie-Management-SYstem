
import React from 'react';
import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ children }) => {
    const vendorId = localStorage.getItem('vendorId');
    return vendorId ? children : <Navigate to="/vendor/login" />;
};

export default ProtectedRoute;
