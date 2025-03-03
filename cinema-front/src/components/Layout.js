
import React from 'react';
import './header.css';  
import './footer.css';  

const Layout = ({ children }) => {
  return (
    <div>
      <header>
        <div className="container">
          <h1 class="h1">Cinema</h1>
          <nav>
            <a href="/">Home</a>
            <a href="/vendor/login">Login</a>
            <a href="/vendor/dashboard">Dashboard</a>
            <a href="/vendor/register">Registration</a>
          </nav>
        </div>
      </header>

      <div className="main-content">{children}</div>

      <footer>
        <p>&copy; 2024 Cinema App. All rights reserved.</p>
      </footer>
    </div>
  );
};

export default Layout;