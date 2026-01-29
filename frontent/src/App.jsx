
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import Login from './login/login'
import HomePage from './homepage/HomePage'

function App() {
  return (
    <BrowserRouter>
        <Routes>
          <Route path='/login' element={<Login/>}/>
          <Route path='*' element={<HomePage/>}/>
        </Routes>
    </BrowserRouter>
  )
}

export default App
