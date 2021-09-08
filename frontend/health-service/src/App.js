import './App.css';
import React from 'react';
import {lastIndexOf, substr} from '@7urtle/lambda';
import {BrowserRouter as Router} from 'react-router-dom';
import Routes from './Routes';

const getBasename = path => substr(lastIndexOf('/')(path))(0)(path);

function App() {
  return (    
    <Router basename={getBasename(window.location.pathname)}>
      <Routes />
    </Router>    
  );
}

export default App;