import React from 'react';
import {Row, Col } from 'react-bootstrap';
import './signInForm.css';
import {Link} from 'react-router-dom';

class SignInForm extends React.Component{
    render(){
        return (
            <>
                <Row>
                    <Col className="form-box" md={{ span: 5, offset: 3 }}>
                        <Row>
                            <Col md={{ offset: 5 }} xs={{ offset: 4 }}>
                                <p className="form-title">Entrar</p>                                
                            </Col>
                        </Row>
                        <Row>
                            <Col md={{ offset: 4 }} xs={{ offset: 2 }}>                                
                                <p className="form-subtitle">Informe seu usuário e senha para entrar.</p>
                            </Col>
                        </Row>
                        <Row>
                            <Col md={{ offset: 2 }}>
                                <input className="form-input" type="text" placeholder="E-mail" />                    
                            </Col>
                        </Row>
                        <Row>
                            <Col md={{ offset: 2 }}>                                        
                                <input className="form-input" type="password" placeholder="Senha" />
                            </Col>                                                
                        </Row>
                        <Row>
                            <Col md={{ offset: 2 }}>
                                <button className="form-button">Entrar</button>                    
                            </Col>
                        </Row>
                        <Row>
                            <Col md={{ offset: 2 }}>                                
                                <p className="form-aux-text">Não tem uma conta? <Link to="/signUp">Cadastre-se</Link></p>                                
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </>
        );
    }
}

export default SignInForm;