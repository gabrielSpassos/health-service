import React from 'react';
import './verticalNavBar.css';
import {Link} from 'react-router-dom';
import { FaBars } from 'react-icons/fa';

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
            return null;
        }else{
            return <FaBars size={28} onClick={this.toggleSideBar} />; 
        }                    
    }

    render(){
        return (        
            <> 
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
                        </ul>
                    </div>
                </div>         
            </>     
        );
    }
}

export default NavBar;