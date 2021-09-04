import React from 'react';
import SignInForm from '../components/signInForm/signInForm';
import NavBar from '../components/navBar/navBar';


function SignIn() {
    return (        
      <>
        <NavBar isSignIn={true}/>
        <SignInForm />
      </>     
    );
  }

export default SignIn;