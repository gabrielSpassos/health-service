import React from 'react';
import './anamnesis.css';
import axios from 'axios';
import Cookies from 'js-cookie';

class Anamnesis extends React.Component{
    constructor(){
        super();
        this.state={
            anamnesis: {dorCabeca: true, tontura: true, enjoo: true, fadiga: true, tremores: true, zumbidos: false, outrasDores: false, desorientacao: false, observacao: "abc", id: 0},        
            errors: {},
            message: ""
        }
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount(){
        let that = this;
        const token = 'Bearer ' + Cookies.get('token');

        axios({
            method: 'get',
            url: 'http://localhost:8080/v1/patients',
            headers: { "Authorization" : "Bearer" + token },
            params: {
                patientId: "1"
            } 
        })
        .then(function (response) { 
            if (response.status === 200){        
                that.setState({medicalRecordId: response.data['registries'][0]})  
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
        e.preventDefault();
        
        let that = this;
        const token = 'Bearer ' + Cookies.get('token');
        let bodyJson = JSON.stringify(this.state.anamnesis);
        axios({
            method: 'put',
            url: 'http://localhost:8080/v1/patients',
            headers: { "Authorization" : "Bearer" + token },
            params: {
                id: this.state.anamnesis.id
            },
            body: bodyJson  
        })
        .then(function (response) { 
            if (response.status === 200){           
                that.setState({message: "Formulário de anamnese atualizado com sucesso."}); 
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
        const { anamnesis } = this.state;
        return (
            <>  
                <div className="row d-flex justify-content-start">                                                                
                    <form onSubmit={this.handleSubmit}>
                        <div className="row">
                            {this.state.errors['except'] && <div className="alert alert-danger">{this.state.errors['except']}</div>}
                            {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
                            <div className="col">
                                <div className="form-check">
                                    <input type="checkbox" name="dorCabeca" id="dorCabeca" onChange={this.handleChange} checked={anamnesis.dorCabeca} className="form-check-input" />
                                    <label for="name">Dor de cabeça</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="tontura" id="tontura" onChange={this.handleChange} checked={anamnesis.tontura} className="form-check-input" />
                                    <label for="name">Tontura</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="enjoo" id="enjoo" onChange={this.handleChange} checked={anamnesis.enjoo} className="form-check-input" />
                                    <label for="name">Enjoo</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="fadiga" id="fadiga" onChange={this.handleChange} checked={anamnesis.fadiga} className="form-check-input" />
                                    <label for="name">Fadiga</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="tremores" id="tremores" onChange={this.handleChange} checked={anamnesis.tremores} className="form-check-input" />
                                    <label for="name">Tremores</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="zumbidos" id="zumbidos" onChange={this.handleChange} checked={anamnesis.zumbidos} className="form-check-input" />
                                    <label for="name">Zumbidos</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="outrasDores" id="outrasDores" onChange={this.handleChange} checked={anamnesis.outrasDores} className="form-check-input" />
                                    <label for="name">Outras dores</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="desorientacao" id="desorientacao" onChange={this.handleChange} checked={anamnesis.desorientacao} className="form-check-input" />
                                    <label for="name">Desorientação</label>
                                </div>
                            </div>    
                        </div>
                        <br />
                        <div className="row">
                            <div className="col">                                                
                                <textarea className="txtBox" name="observacao" id="observacao" value={anamnesis.observacao} placeholder="Observações"></textarea>
                            </div>
                        </div>
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