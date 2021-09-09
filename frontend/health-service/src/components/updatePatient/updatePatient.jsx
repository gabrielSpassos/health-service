import React from 'react';
import './updatePatient.css';
import axios from 'axios';
import Cookies from 'js-cookie';
import NavBar from './../navBar/navBar';
import VerticalNavBar from './../verticalNavBar/verticalNavBar';
import General from './../general/general';
import Anamnesis from '../anamnesis/anamnesis';

class UpdatePatient extends React.Component{
    constructor(){
        super();
        this.state={
            patient: {name: "Matheus da Rosa Pinheiro", cpf: "00000000000", rg: "00000000", sex: "MALE", phone: "51999334183", birthdate: "29/11/1994"},        
            patients: [{name: "", cpf: "", rg: "", sex: "", phone: "", birthdate: ""}],
            errors: {}
        }
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit = e =>{
        e.preventDefault();
        
        let that = this;
        const token = 'Bearer ' + Cookies.get('token');
        console.log('token: ', token);

        axios({
            method: 'get',
            url: 'http://localhost:8080/v1/patients',
            headers: { "Authorization" : "Bearer" + token } 
        })
        .then(function (response) { 
            if (response.status === 200){           
                console.log('bombou', response);   
            }                  
        })
        .catch(function (error) {
            const errors = {};
            errors.except = 'Erro ao enviar formulário, contate o administrador do sistema.: ' + error;
            that.setState({errors: errors});
            console.log('Error Debug: ', error);
        });
    }

    handleChange = ({currentTarget: input}) =>{        
        const patient = {...this.state.patient};        
        patient[input.name] = input.value;
        this.setState({ patient });
    }

    render(){
        const { patient } = this.state;

        return (
            <>  
                <div className="container-fluid">
                    <NavBar buttonType={'home'}/>                
                    <div className="row">
                        <VerticalNavBar />  
                        <div className="col-8">                    
                            <div className="row d-flex justify-content-start">                                
                                <div className="form-box">
                                    <h2>Prontuário eletrônico</h2>
                                    <br />
                                    <ul class="nav nav-tabs">
                                        <li class="nav-item">
                                            <a class="nav-link active" aria-current="page" href="#">Informações gerais</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">Anamnese</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">Monitoramento</a>
                                        </li>
                                    </ul>
                                </div>                                
                            </div>
                            <General />
                        </div>
                    </div>
                </div>          
            </>
        );
    }
}

export default UpdatePatient;