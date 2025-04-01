import React from 'react';

function Header() {
  return (
    <header className="bg-blue-500 text-white p-4 flex justify-between items-center">
      <div className="text-2xl font-bold">Creative-lab</div>
      <nav>
        <ul className="flex space-x-4">
          <li><a href="/" className="hover:text-blue-200">홈</a></li>
          <li><a href="/login" className="hover:text-blue-200">로그인</a></li>
          <li><a href="/upload" className="hover:text-blue-200">업로드</a></li>
        </ul>
      </nav>
    </header>
  );
}

export default Header;