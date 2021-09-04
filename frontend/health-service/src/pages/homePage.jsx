import React from 'react';
import Home from '../components/signInForm/signInForm';
import NavBar from '../components/navBar/navBar';


function HomePage() {
    return (        
      <>
        <NavBar isSignIn={true}/>
        <h1>Hello World</h1>
      </>     
    );
  }

export default HomePage;