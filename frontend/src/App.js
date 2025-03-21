import logoImg from './img_upload.png';
import './Reset.css';
import './App.css';

function App() {
  return (
    <div className="LoginPg">
      <header className="App-header">
        <img src="{logoImg}" alt="creative-lab" style={{width: '100px'}}/>
        <input type="text" className="input-style" placeholder="아이디를 입력해 주세요."/>
        <input type="password" className="input-style" placeholder="비밀번호를 입력해 주세요."/>
        <input type="checkbox" id="saveId"/><label for="saveId"><span></span>아이디 저장</label>
        <button className="login-button" type="button">로그인</button>
        <a href="">아이디/비밀번호 찾기</a>
      </header>
    </div>
}

export default App;