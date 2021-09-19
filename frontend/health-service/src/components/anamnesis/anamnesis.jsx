import React from 'react';
import './anamnesis.css';
import axios from 'axios';
import Cookies from 'js-cookie';

class Anamnesis extends React.Component{
    constructor(){
        super();
        this.state={
            patient: {name: "Matheus da Rosa Pinheiro", cpf: "00000000000", rg: "00000000", sex: "MALE", phone: "51999334183", birthdate: "29/11/1994"},        
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
                <div className="row d-flex justify-content-start">                                                                
                    <form onSubmit={this.handleSubmit}>
                        <div className="row">
                            <div className="col">
                                <div className="form-check">
                                    <input type="checkbox" name="dorCabeca" id="dorCabeca" onChange={this.handleChange} value={patient.name} className="form-check-input" />
                                    <label for="name">Dor de cabeça</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="tontura" id="tontura" onChange={this.handleChange} value={patient.name} className="form-check-input" />
                                    <label for="name">Tontura</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="enjoo" id="enjoo" onChange={this.handleChange} value={patient.name} className="form-check-input" />
                                    <label for="name">Enjoo</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="fadiga" id="fadiga" onChange={this.handleChange} value={patient.name} className="form-check-input" />
                                    <label for="name">Fadiga</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="tremores" id="tremores" onChange={this.handleChange} value={patient.name} className="form-check-input" />
                                    <label for="name">Tremores</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="zumbidos" id="zumbidos" onChange={this.handleChange} value={patient.name} className="form-check-input" />
                                    <label for="name">Zumbidos</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="outrasDores" id="outrasDores" onChange={this.handleChange} value={patient.name} className="form-check-input" />
                                    <label for="name">Outras dores</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="desorientacao" id="desorientacao" onChange={this.handleChange} value={patient.name} className="form-check-input" />
                                    <label for="name">Desorientação</label>
                                </div>
                            </div>    
                        </div>
                        <br />
                        <div className="row">
                            <div className="col">                                                
                                <textarea className="txtBox" name="observacao" id="observacao" placeholder="Observações"></textarea>
                            </div>
                        </div>
                        <br />
                        <div className="row">
                            <div className="col-md-3">                                                
                                <input className="form-button" type="submit" value="Atualizar informações" />
                            </div>
                        </div>                                                                                                                                                                
                    </form>    
                </div>                                    
            </>
        );
    }
}

export default Anamnesis;