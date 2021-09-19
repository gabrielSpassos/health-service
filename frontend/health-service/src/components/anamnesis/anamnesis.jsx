import React from 'react';
import './anamnesis.css';
import axios from 'axios';
import Cookies from 'js-cookie';

class Anamnesis extends React.Component{
    constructor(){
        super();
        this.state={
            anamnesis: {patientHasHeadache: true, patientHasDizziness: true, patientHasNausea: true,
                        patientHasFatigue: true, patientHasTremors: true, patientFeelsTinnitus: false,
                        patientFeelsPain: false, patientHasOtherSymptom: false, description: "abc", id: 0},        
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
                                    <input type="checkbox" name="patientHasHeadache" id="patientHasHeadache" onChange={this.handleChange} checked={anamnesis.patientHasHeadache} className="form-check-input" />
                                    <label for="patientHasHeadache">Dor de cabeça</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="patientHasDizziness" id="patientHasDizziness" onChange={this.handleChange} checked={anamnesis.patientHasDizziness} className="form-check-input" />
                                    <label for="patientHasDizziness">Tontura</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="patientHasNausea" id="patientHasNausea" onChange={this.handleChange} checked={anamnesis.patientHasNausea} className="form-check-input" />
                                    <label for="patientHasNausea">Enjoo</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="patientHasFatigue" id="patientHasFatigue" onChange={this.handleChange} checked={anamnesis.patientHasFatigue} className="form-check-input" />
                                    <label for="patientHasFatigue">Fadiga</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="patientHasTremors" id="patientHasTremors" onChange={this.handleChange} checked={anamnesis.patientHasTremors} className="form-check-input" />
                                    <label for="patientHasTremors">Tremores</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="patientFeelsTinnitus" id="patientFeelsTinnitus" onChange={this.handleChange} checked={anamnesis.patientFeelsTinnitus} className="form-check-input" />
                                    <label for="patientFeelsTinnitus">Zumbidos</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="patientFeelsPain" id="patientFeelsPain" onChange={this.handleChange} checked={anamnesis.patientFeelsPain} className="form-check-input" />
                                    <label for="patientFeelsPain">Outras dores</label>
                                </div>
                                <div className="form-check">
                                    <input type="checkbox" name="patientHasOtherSymptom" id="patientHasOtherSymptom" onChange={this.handleChange} checked={anamnesis.patientHasOtherSymptom} className="form-check-input" />
                                    <label for="patientHasOtherSymptom">Desorientação</label>
                                </div>
                            </div>    
                        </div>
                        <br />
                        <div className="row">
                            <div className="col">                                                
                                <textarea className="txtBox" name="description" id="description" value={anamnesis.description} placeholder="Observações"></textarea>
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