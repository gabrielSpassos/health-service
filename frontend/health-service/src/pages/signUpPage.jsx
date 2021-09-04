import React from 'react';
import SignUpForm from '../components/signUpForm/signUpForm';
import NavBar from '../components/navBar/navBar';

function SignUp() {
    return (        
      <>
        <NavBar isSignIn={false}/>
        <SignUpForm />
      </>     
    );
  }

export default SignUp;