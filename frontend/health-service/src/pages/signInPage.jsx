import React from 'react';
import SignInForm from '../components/signInForm/signInForm';
import NavBar from '../components/navBar/navBar';


function SignIn() {
    return (        
      <>
        <div className="container-fluid">
          <NavBar isSignIn={true}/>
          <SignInForm />
        </div>
      </>     
    );
  }

export default SignIn;