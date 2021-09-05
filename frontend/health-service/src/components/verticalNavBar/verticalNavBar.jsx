import React from 'react';
import { Row, Col } from 'react-bootstrap';
import './verticalNavBar.css';
import {Link} from 'react-router-dom';
import { FaBars } from 'react-icons/fa';
import { AiOutlineClose } from 'react-icons/ai';

class NavBar extends React.Component {
    state = {
        sidebar: false
    } 

    constructor() {
        super();     
        this.toggleSideBar = this.toggleSideBar.bind(this);
    }  

    toggleSideBar () {
        const currentState = this.state.sidebar;
        this.setState({ sidebar: !currentState });
        console.log(this.state.sidebar);
    }

    render(){
        return (        
            <>        
                <div className="navbar">
                    <Link to="#" className='menu-bars'>
                        <FaBars onClick={this.toggleSideBar} />
                    </Link>
                </div>
                <nav className={this.sidebar ? 'nav-menu active' : 'nav-menu'}>
                    <ul className='nav-menu-items' onClick={this.toggleSideBar}>
                        <li className="navbar-toggle">
                            <Link to="#" className="menu-bars">
                                <AiOutlineClose />
                            </Link>
                        </li>
                        <li className="nav-text">
                            <Link to="/">
                                <span>Cadastrar institucionalizado</span>   
                            </Link>
                        </li>
                    </ul>
                </nav>               
            </>     
        );
    }
}


export default NavBar;