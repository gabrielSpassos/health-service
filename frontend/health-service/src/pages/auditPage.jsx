import React from 'react';
import NavBar from '../components/navBar/navBar';
import VerticalNavBar from '../components/verticalNavBar/verticalNavBar';
import { Redirect } from 'react-router-dom';
import Cookies from 'js-cookie';

class HomePage extends React.Component {    
    constructor(){
      super();
      this.state={        
        ck: false
      }      
    }    

    componentDidMount(){
      if (Cookies.get('token') == null){
        this.setState({ck: true});        
      }else{
        this.setState({ck: false});
      }    
    }

    render(){
      if (this.state.ck){
        return <Redirect to="/" />;
      }    
      return (        
        <>
          <div className="container-fluid">
            <NavBar buttonType={'home'}/>
            <div className="row">
              <VerticalNavBar />
                         
            </div>
          </div>
        </>     
      );
    }
  }

export default HomePage;