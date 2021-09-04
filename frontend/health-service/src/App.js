import './App.css';
import {Route, Switch } from 'react-router-dom';
import SignIn from './pages/signInPage';
import SignUp from './pages/signUpPage';
import HomePage from './pages/homePage';

function App() {
  return (    
      <Switch>        
        <Route path="/signUp" component={SignUp}/>
        <Route path="/" component={HomePage}/>
      </Switch>
  );
}

export default App;