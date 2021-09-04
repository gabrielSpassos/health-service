import React from 'react';
import { Row, Col } from 'react-bootstrap';
import './navBar.css';
import {Link} from 'react-router-dom';

class NavBar extends React.Component {
    state = {
        buttonType: this.props.isSignIn
    };

    render(){
        return (        
            <>        
                <Row className="navBar">
                    <Col>
                        <img src="app-icon.png" height="50px" alt="Logo of the application"></img>
                    </Col>
                    <Col>
                        {this.renderNavButton()}
                    </Col>
                </Row>              
            </>     
        );
    }

    renderNavButton(){
        if (this.state.buttonType) return <Link to="/signUp" className="navBarSignButton">Cadastre-se</Link>;

        return <Link className="navBarSignButton" to="/signIn">Entrar</Link>
    }
}


export default NavBar;