import React from 'react';
import NavBar from '../components/navBar/navBar';
import VerticalNavBar from '../components/verticalNavBar/verticalNavBar';
import { Router, Switch, Route } from 'react-router-dom';


function HomePage() {
    return (        
      <>
        <NavBar isSignIn={true}/>
        <VerticalNavBar />
        <Switch>
            <Route path="/" />
        </Switch>        
      </>     
    );
  }

export default HomePage;