import React from 'react';
import NavBar from '../components/navBar/navBar';
import VerticalNavBar from '../components/verticalNavBar/verticalNavBar';
import { Router, Switch, Route } from 'react-router-dom';
import SearchPatient from '../components/searchPatient/searchPatient';
import RegisterPatient from '../components/registerPatient/registerPatient';


function HomePage() {
    return (        
      <>                
        <NavBar buttonType={'home'}/>
        <div className="row">
          <div className="col-md-2">
            <VerticalNavBar />
          </div>
          <div className="col-md-10">
            <Switch>
                <Route path="/searchPatient" component={SearchPatient}/>
                <Route path="/registerPatient" component={RegisterPatient}/>
            </Switch>        
          </div>
        </div>
      </>     
    );
  }

export default HomePage;