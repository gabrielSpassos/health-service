import React from 'react';
import {Row, Col } from 'react-bootstrap';
import {Link} from 'react-router-dom';
import axios from 'axios';

class RegisterPatient extends React.Component{
    render(){
        return (
            <>
                <div className="row form-register-title">                    
                    <h1>Formulário de cadastro</h1>
                </div>
                <div className="row">
                    <div className="className col-md-6">
                        <form>
                            <input type="" placeholder="Nome do paciente" />
                            <input type="" placeholder="CPF" />
                            <button>Entrar</button>   
                        </form>   
                    </div>
                </div>  
            </>
        );
    }
}

export default RegisterPatient;