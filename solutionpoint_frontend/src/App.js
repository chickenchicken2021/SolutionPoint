import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';
import axios from 'axios';

function App() {
  // 요청받은 정보를 받아둘 변수
  const [testStr, setTestStr] = useState("");
  // 변수 초기화
  function callback(str){
    setTestStr(str);
  }  
  // 첫번째 렌더링을 마친후 실행 ㅎㅎ(제일먼저 실행)
  useEffect(() => {
    axios({ url: "/home", method: "GET" }).then((res) => {
      callback(res.data);
    });
  }, []);



  return (
    <div className="App">
      <header className='App-header'>
        {testStr}
      </header>
    </div>
  );
}

export default App;
