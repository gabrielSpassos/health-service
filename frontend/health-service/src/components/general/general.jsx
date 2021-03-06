import React from 'react';
import './general.css';
import axios from 'axios';
import Cookies from 'js-cookie';
import { Redirect } from 'react-router-dom/cjs/react-router-dom.min';

class General extends React.Component{
    constructor(props){
        super(props);
        this.state={
            patient: {name: "Matheus da Rosa Pinheiro", cpf: "00000000000", rg: "00000000", sex: "MALE", phone: "51999334183", birthdate: "29/11/1994"},        
            errors: {}
        }
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount(){
        let that = this;
        const token = 'Bearer ' + Cookies.get('token');
        axios({
            method: 'get',
            url: 'http://localhost:8080/v1/patients/'+this.props.id,
            headers: { "Authorization" : token, "content-type": "application/json" }
        })
        .then(function (response) { 
            if (response.status === 200){        
                that.setState({patient: response.data});  
            }                  
        })
        .catch(function (error) {
            const errors = {};
            errors.except = 'Erro ao carregar formulário, contate o administrador do sistema.: ' + error;
            that.setState({errors: errors});
            console.log('Error Debug: ', error);
        });
    }

    handleSubmit = e =>{
    }

    handleChange = ({currentTarget: input}) =>{        
        const patient = {...this.state.patient};        
        patient[input.name] = input.value;
        this.setState({ patient });
    }

    render(){
        const { patient } = this.state;

        if (this.props.id == null){
            return <Redirect to="/home" />;
        }

        return (
            <>  
                <div className="row d-flex justify-content-start">                                                                                  
                    <form onSubmit={this.handleSubmit}>
                        <div className="row">
                            {this.state.errors['except'] && <div className="alert alert-danger">{this.state.errors['except']}</div>}
                            {this.state.message && <div className="alert alert-success">{this.state.message}</div>}  
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
                            <div className="col">
                                <input type="date" name="birthdate" id="birthdate" onChange={this.handleChange} value={patient.birthdate} className="form-input" placeholder="Data de nascimento" />
                                {this.state.errors['birthdate'] && <div className="alert alert-danger">{this.state.errors['birthdate']}</div>}
                            </div>
                            <div className="col">
                                <input type="text" name="phone" id="phone" onChange={this.handleChange} value={patient.phone} className="form-input" placeholder="Telefone de contato" />                                
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
                    </form>    
                </div>                                    
            </>
        );
    }
}

export default General;