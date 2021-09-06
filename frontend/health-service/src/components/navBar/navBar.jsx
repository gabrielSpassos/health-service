import React from 'react';
import { Row, Col } from 'react-bootstrap';
import './navBar.css';
import {Link} from 'react-router-dom';
import { CgProfile } from 'react-icons/cg';

class NavBar extends React.Component {
    state = {
        buttonType: this.props.buttonType
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
        if (this.state.buttonType === 'SignIn'){
            return <Link to="/signUp" className="navBarSignButton">Cadastre-se</Link>;
        }else if (this.state.buttonType === 'SignUp'){
            return <Link className="navBarSignButton" to="/signIn">Entrar</Link>
        }else{
            return null; /*<Link className="navBarIcon" to="/"><CgProfile size={42}/></Link>;*/
        }                
    }
}


export default NavBar;