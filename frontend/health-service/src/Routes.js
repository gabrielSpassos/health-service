import {Route, Switch } from 'react-router-dom';
import SignIn from './pages/signInPage';
import SignUp from './pages/signUpPage';
import HomePage from './pages/homePage';
import SearchPatient from './components/searchPatient/searchPatient';

function Routes() {
    return ( 
        <Switch>            
            <Route path="/searchPatient" component={SearchPatient}/>
            <Route path="/registerPatient" component={SearchPatient}/>                    
            <Route path="/signUp" component={SignUp}/>        
            <Route path="/home" component={HomePage} />
            <Route path="/" component={SignIn}/>            
        </Switch>
    );
}

export default Routes;