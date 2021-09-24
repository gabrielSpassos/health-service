import React from 'react';
import NavBar from '../components/navBar/navBar';
import VerticalNavBar from '../components/verticalNavBar/verticalNavBar';
import { Redirect } from 'react-router-dom';
import Cookies from 'js-cookie';
import Anamnesis from '../components/anamnesis/anamnesis';

class UpdateAnamnesisPage extends React.Component {    
    constructor(props){
      super(props);
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
              <div className="col-8">                    
                  <div className="row d-flex justify-content-start">                                
                      <div className="form-box">
                        <Anamnesis id={this.props.location.id} tipo={this.props.location.tipo} medicalRecordId={this.props.location.medicalRecordId}/>          
                      </div>
                  </div>            
              </div>
            </div>
          </div>
        </>     
      );
    }
  }

export default UpdateAnamnesisPage;