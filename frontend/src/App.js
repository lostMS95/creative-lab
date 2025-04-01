import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './pages/home/HomePage';
import UploadPage from './pages/home/UploadPage';
import Header from './Components/Header';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/register/RegisterPage';

import './Reset.css';
import './App.css';
import './index.css'; // Tailwind 적용
import './sub.css';

function App() {
  return (
    <Router>
      <Header />
      <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/upload" element={<UploadPage />} />
          <Route path="/register" element={<RegisterPage />} />
        {/* 다른 라우트들도 여기에 추가 가능 */}
      </Routes>
    </Router>
  )
}

export default App;