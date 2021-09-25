import React from 'react';
import NavBar from '../components/navBar/navBar';
import VerticalNavBar from '../components/verticalNavBar/verticalNavBar';
import { Redirect } from 'react-router-dom';
import Cookies from 'js-cookie';
import AuditList from '../components/audit-list/audit-list';

class AuditPage extends React.Component {    
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
              <AuditList />           
            </div>
          </div>
        </>     
      );
    }
  }

export default AuditPage;