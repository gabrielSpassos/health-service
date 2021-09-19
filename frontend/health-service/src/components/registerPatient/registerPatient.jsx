import React from 'react';
import './registerPatient.css';
import axios from 'axios';
import Cookies from 'js-cookie';
import NavBar from './../navBar/navBar';
import VerticalNavBar from './../verticalNavBar/verticalNavBar';

class RegisterPatient extends React.Component{
    constructor(){
        super();
        this.state={        
            patient: {name: "", cpf: "", rg: "", sex: "", phone: "", birthdate: ""},
            errors: {},
            message: ""
        }
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    validate = () => {
        const errors = {};
        const { patient } = this.state;

        if (patient.name.trim() === ''){
            errors.name = 'O campo Nome completo é obrigatório.'
        }
        if (patient.cpf.trim() === ''){
            errors.cpf = 'O campo CPF é obrigatório.'
        }
        if (patient.rg.trim() === ''){
            errors.rg = 'O campo RG é obrigatório.'
        }
        if (patient.birthdate.trim() === ''){
            errors.birthdate = 'O campo Data de nascimento é obrigatório.'
        }
        if (patient.sex.trim() === ''){
            errors.sex = 'O campo Sexo é obrigatório.'
        }

        return Object.keys(errors).length === 0 ? null : errors;
    };

    handleSubmit = e =>{
        e.preventDefault();

        const errors = this.validate();
        this.setState({errors: errors || {}});

        if (errors) return;

        let that = this;
        let bodyJson = JSON.stringify(this.state.patient);
        const token = 'Bearer ' + Cookies.get('token');

        axios({
            method: 'post',
            url: 'http://localhost:8080/v1/patients',
            data: bodyJson,
            headers: { "Authorization" : "Bearer" + token } 
        })
        .then(function (response) { 
            if (response.status === 200){
                that.setState({message: "Paciente cadastrado com sucesso."});          
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
                                {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
                                <div className="form-box">
                                    <h2>Cadastrar institucionalizado</h2>
                                    <br />
                                    <form onSubmit={this.handleSubmit}>
                                        <div className="row">
                                            <div className="col">
                                                <input type="text" name="name" maxLength="45" id="name" onChange={this.handleChange} value={patient.name} className="form-input" placeholder="Nome completo" />
                                                {this.state.errors['name'] && <div className="alert alert-danger">{this.state.errors['name']}</div>}
                                            </div>
                                            <div className="col">
                                                <input type="text" name="rg" id="rg" maxLength="9" onChange={this.handleChange} value={patient.rg} className="form-input" placeholder="RG" />
                                                {this.state.errors['rg'] && <div className="alert alert-danger">{this.state.errors['rg']}</div>}
                                            </div>
                                            <div className="col">
                                                <input type="text" name="cpf" id="cpf" maxLength="11" onChange={this.handleChange} value={patient.cpf} className="form-input" placeholder="CPF" />
                                                {this.state.errors['cpf'] && <div className="alert alert-danger">{this.state.errors['cpf']}</div>}
                                            </div>
                                        </div>
                                        <div className="row">
                                            <div className="col">
                                                <input type="date" name="birthdate" id="birthdate" onChange={this.handleChange} value={patient.birthdate} className="form-input" placeholder="Data de nascimento" />
                                                {this.state.errors['birthdate'] && <div className="alert alert-danger">{this.state.errors['birthdate']}</div>}
                                            </div>
                                            <div className="col">
                                                <input type="text" name="phone" id="phone" maxLength="11" onChange={this.handleChange} value={patient.phone} className="form-input" placeholder="Telefone de contato" />                                
                                            </div>
                                            <div className="col">
                                                <select name="sex" id="sex" onChange={this.handleChange} className="form-input" value={patient.sex}>
                                                    <option value="">Selecionar</option>
                                                    <option value="MALE">Masculino</option>
                                                    <option value="FEMALE">Feminino</option>
                                                </select>
                                                {this.state.errors['sex'] && <div className="alert alert-danger">{this.state.errors['sex']}</div>}
                                            </div>    
                                        </div>
                                        <div className="row">
                                            <div className="col-md-3">                                                
                                                <input className="form-button" type="submit" value="Cadastrar" />
                                            </div>                                                                                        
                                        </div>                                                                                                                                                           
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div> 
                </div>             
            </>
        );
    }
}

export default RegisterPatient;