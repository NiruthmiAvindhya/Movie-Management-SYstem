import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
    const vendorId = localStorage.getItem('vendorId');

    return (
        <header>
            
            <nav>
                
                <Link to="/">Home</Link>
                {vendorId ? (
                    <>
                        <Link to="/vendor/dashboard">My Dashboard</Link>
                        <button onClick={() => localStorage.clear()}>Logout</button>
                    </>
                ) : (
                    <>
                        <Link to="/vendor/login">Vendor Login</Link>
                        <Link to="/vendor/register">Vendor Registration</Link>
                    </>
                )}
            </nav>
        </header>
    );
};

export default Header;