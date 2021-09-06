import React from 'react';
import NavBar from '../components/navBar/navBar';
import VerticalNavBar from '../components/verticalNavBar/verticalNavBar';
import { Router, Switch, Route } from 'react-router-dom';
import SearchPatient from '../components/searchPatient/searchPatient';


function HomePage() {
    return (        
      <>
        <NavBar buttonType={'home'}/>
        <VerticalNavBar />
          <Switch>
              <Route path="/searchPatient" component={SearchPatient}/>
              <Route path="/registerPatient" component={SearchPatient}/>
          </Switch>        
      </>     
    );
  }

export default HomePage;