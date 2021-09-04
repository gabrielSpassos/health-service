import React from 'react';
import {Row, Col } from 'react-bootstrap';
import './signUpForm.css';
import {Link} from 'react-router-dom';

class SignUpForm extends React.Component{
    render(){
        return (
            <>
                <Row>
                    <Col className="form-box" md={{ span: 5, offset: 3 }}>
                        <Row>
                            <Col md={{ offset: 3 }} xs={{ offset: 2 }}>
                                <p className="form-title">Crie uma conta</p>                                
                            </Col>
                        </Row>
                        <Row>
                            <Col md={{ offset: 3 }} xs={{ offset: 2 }}>                                
                                <p className="form-subtitle">Informe seus dados para criar uma conta</p>
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
                                <button onClick={this.handleCreateUser} className="form-button">Cadastrar</button>                    
                            </Col>
                        </Row>
                        <Row>
                            <Col md={{ offset: 2 }}>                                
                                <p className="form-aux-text">Já tem uma conta? <Link to="/signIn">Entrar</Link></p>                                
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </>
        );
    }
}

export default SignUpForm;