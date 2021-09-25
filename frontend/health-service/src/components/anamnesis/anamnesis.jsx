import React from 'react';
import './anamnesis.css';
import axios from 'axios';
import Cookies from 'js-cookie';
import { Redirect } from 'react-router-dom/cjs/react-router-dom.min';

class Anamnesis extends React.Component{
    constructor(props){
        super(props);
        this.state={
            anamnesis: {patientHasHeadache: false, patientHasDizziness: false, patientHasNausea: false,
                        patientHasFatigue: false, patientHasTremors: false, patientFeelsTinnitus: false,
                        patientFeelsPain: false, patientHasOtherSymptom: false, description: "", id: 0},        
            errors: {},
            message: "",
            medicalRecordId: 0,
            redirect: false
        }
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount(){
        if (this.props.tipo === 'create') {return}

        let that = this;
        const token = 'Bearer ' + Cookies.get('token');

        axios({
            method: 'get',
            url: 'http://localhost:8080/v1/registries/'+this.props.id,
            headers: { "Authorization" : token }
        })
        .then(function (response) { 
            if (response.status === 200){        
                that.setState({anamnesis: response.data});
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
        if (this.props.tipo === 'create'){
            console.log('criou');
            axios({
                method: 'post',
                url: 'http://localhost:8080/v1/medical-records/'+this.props.medicalRecordId+'/registries',
                headers: { "Authorization" : token, "Content-Type": "application/json" },
                data: bodyJson  
            })
            .then(function (response) { 
                if (response.status === 201){           
                    that.setState({message: "Formulário de anamnese criado com sucesso."});
                    that.setState({redirect: true}); 
                }                  
            })
            .catch(function (error) {
                const errors = {};
                errors.except = 'Erro ao enviar formulário, contate o administrador do sistema.: ' + error;
                that.setState({errors: errors});
                console.log('Error Debug: ', error);
            });
        }else{
            axios({
                method: 'put',
                url: 'http://localhost:8080/v1/registries/'+this.state.anamnesis.id,
                headers: { "Authorization" : token,"content-type": "application/json" },
                data: bodyJson  
            })
            .then(function (response) {
                if (response.status === 200){           
                    that.setState({message: "Formulário de anamnese atualizado com sucesso."});                    
                    that.setState({redirect: true});  
                }                  
            })
            .catch(function (error) {
                const errors = {};
                errors.except = 'Erro ao enviar formulário, contate o administrador do sistema.: ' + error;
                that.setState({errors: errors});
                console.log('Error Debug: ', error);
            });
        }
    }

    handleChange = ({currentTarget: input}) =>{        
        const anamnesis = {...this.state.anamnesis};
        if (input.name !== 'description'){
            let inputChecked = input['checked'];          
            anamnesis[input.name] = inputChecked;                 
        }else{
            anamnesis[input.name] = input.value;
        }        
        
        this.setState({ anamnesis });
    }

    render(){
        const { anamnesis } = this.state;

        return (
            <>  
                <div className="row d-flex justify-content-start">
                    <h2>Formulário de anamnese</h2>
                    <br />                                                                
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
                                <textarea className="txtBox" name="description" id="description" value={anamnesis.description} onChange={this.handleChange} placeholder="Observações"></textarea>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-md-3">                                                
                                <input className="form-button" type="submit" value="Salvar" />
                            </div>
                        </div>                                                                                                                                                                
                    </form>    
                </div>                                    
            </>
        );
    }
}

export default Anamnesis;