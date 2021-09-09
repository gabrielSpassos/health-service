import React from 'react';
import './searchPatient.css';
import axios from 'axios';
import Cookies from 'js-cookie';
import NavBar from './../navBar/navBar';
import VerticalNavBar from './../verticalNavBar/verticalNavBar';
import { Link } from 'react-router-dom';

class SearchPatient extends React.Component{
    constructor(){
        super();
        this.state={
            patient: {name: "", cpf: "", rg: "", sex: "", phone: "", birthdate: ""},        
            patients: [{name: "Matheus da Rosa Pinheiro", cpf: "00000000000", rg: "000000000", sex: "", phone: "", birthdate: "", id: "2"},
                       {name: "Senhorzinho Malandro", cpf: "00000000000", rg: "000000000", sex: "", phone: "", birthdate: "", id: "2"},
            ],
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
                                {this.state.errors['except'] && <div className="alert alert-danger">{this.state.errors['except']}</div>}
                                <div className="form-box">
                                    <h2>Consultar institucionalizado</h2>
                                    <br />
                                    <form onSubmit={this.handleSubmit}>
                                        <div className="row">
                                            <div className="col">
                                                <input type="text" name="name" id="name" onChange={this.handleChange} value={patient.name} className="form-input" placeholder="Nome completo" />
                                                {this.state.errors['name'] && <div className="alert alert-danger">{this.state.errors['name']}</div>}
                                            </div>
                                            <div className="col">
                                                <input type="text" name="rg" id="rg" onChange={this.handleChange} value={patient.rg} className="form-input" placeholder="RG" />
                                                {this.state.errors['rg'] && <div className="alert alert-danger">{this.state.errors['rg']}</div>}
                                            </div>
                                            <div className="col">
                                                <input type="text" name="cpf" id="cpf" onChange={this.handleChange} value={patient.cpf} className="form-input" placeholder="CPF" />
                                                {this.state.errors['cpf'] && <div className="alert alert-danger">{this.state.errors['cpf']}</div>}
                                            </div>
                                        </div>
                                        <div className="row">
                                            <div className="col-md-3">
                                                <input className="form-button" type="submit" value="Buscar" />
                                            </div>
                                        </div>                                                                                                                                                            
                                    </form>
                                </div>
                            </div>
                            {this.state.patients &&
                                <div className="row d-flex justify-content-start">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">Nome</th>
                                                <th scope="col">RG</th>
                                                <th scope="col">CPF</th>
                                                <th scope="col">Ação</th>                                      
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {this.state.patients.map(pt => {
                                                return(
                                                    <tr>
                                                        <th scope="row">{pt.name}</th>
                                                        <td>{pt.rg}</td>
                                                        <td>{pt.cpf}</td>
                                                        <td><Link to="/updatePatient">Visualizar prontuário</Link></td>
                                                    </tr>
                                                ); 
                                            })}                                           
                                        </tbody>
                                    </table>   
                                </div>
                            }    
                        </div>
                    </div>
                </div>          
            </>
        );
    }
}

export default SearchPatient;