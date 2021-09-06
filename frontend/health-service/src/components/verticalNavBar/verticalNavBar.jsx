import React from 'react';
import { Row, Col } from 'react-bootstrap';
import './verticalNavBar.css';
import {Link} from 'react-router-dom';
import { FaBars } from 'react-icons/fa';
import { AiOutlineClose } from 'react-icons/ai';

class NavBar extends React.Component {
    state = {
        sidebar: true
    } 

    constructor() {
        super();     
        this.toggleSideBar = this.toggleSideBar.bind(this);
    }  

    toggleSideBar () {
        const currentState = this.state.sidebar;
        this.setState({ sidebar: !currentState });
    }

    showNavBarIcons = () => {
        if (this.state.sidebar) {
            return null; /*<AiOutlineClose size={28} onClick={this.toggleSideBar} />*/
        }else{
            return <FaBars size={28} onClick={this.toggleSideBar} />; 
        }                    
    }

    render(){
        return (        
            <>  <div class="row">
                    <div className={this.state.sidebar ? 'col-md-2' : ''}>                    
                        <div className={this.state.sidebar ? 'nav-menu active' : 'nav-menu'}>
                            <ul className='nav-menu-items'>                                                    
                                <li className="nav-text">                                                                                  
                                    <span><b>Informações dos pacientes</b></span>
                                    <ul className="nav-menu-sub-item">
                                        <Link to="/registerPatient"> 
                                        <li>Cadastrar paciente</li>
                                        </Link>
                                        <Link to="/searchPatient"> 
                                            <li>Consultar paciente</li>
                                        </Link> 
                                    </ul>                                            
                                </li>                                                                
                                <li className="nav-text">
                                    <Link to="/">                                        
                                        <span><b>Outros</ b></span>                                       
                                    </Link>
                                </li>                                
                            </ul>
                        </div>
                    </div>
                    <div className="col-md-1">
                        <div className={this.state.sidebar ? 'toggleButton activeB' : 'toggleButton'}>
                            <ul> 
                                <li className="navbar-toggle">
                                    <Link to="#" className="menu-bars">
                                        {this.showNavBarIcons()}                                
                                    </Link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>           
            </>     
        );
    }
}

export default NavBar;