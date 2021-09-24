import React from 'react';
import './anamnesisList.css';
import axios from 'axios';
import Cookies from 'js-cookie';
import { Redirect, Link } from 'react-router-dom/cjs/react-router-dom.min';

class AnamnesisList extends React.Component{
    constructor(){
        super();
        this.state={
            anamnesis: [{patientHasHeadache: false, patientHasDizziness: false, patientHasNausea: false,
                        patientHasFatigue: false, patientHasTremors: false, patientFeelsTinnitus: false,
                        patientFeelsPain: false, patientHasOtherSymptom: false, description: "", id: 0}],        
            errors: {},
            message: "",
            medicalRecordId: 0
        }
    }

    componentDidMount(){
        let that = this;
        const token = 'Bearer ' + Cookies.get('token');

        axios({
            method: 'get',
            url: 'http://localhost:8080/v1/patients/'+this.props.id+'/medical-records',
            headers: { "Authorization" : token }
        })
        .then(function (response) { 
            if (response.status === 200){        
                that.setState({anamnesis: response.data['registries']});
                that.setState({medicalRecordId: response.data['id']}); 
            }                         
        })
        .catch(function (error) {
            const errors = {};
            errors.except = 'Erro ao carregar formulário, contate o administrador do sistema.: ' + error;
            that.setState({errors: errors});
            console.log('Error Debug: ', error);
        });    
    }

    redirect(){

    }

    render(){
        const { anamnesis } = this.state;

        if (this.props.id == null){
            return <Redirect to="/home" />;
        }

        return (
            <>  
                <div className="row d-flex justify-content-start">                                                                
                    {anamnesis &&
                    <div className="row d-flex justify-content-start">
                        <table className="table">
                            <thead>
                                <tr>
                                    <th scope="col">Identificador</th>
                                    <th scope="col">Descrição</th>
                                    <th scope="col">Ação</th>                                      
                                </tr>
                            </thead>
                            <tbody>
                                {anamnesis.map(pt => {
                                    return(
                                        <tr>
                                            <th id={'ident'+pt.id} scope="row">{pt.id}</th>
                                            <th id={'description'+pt.id} scope="row">{pt.description}</th>
                                            <td id={'id'+pt.id}><Link to={{pathname: "/anamnesis", id: pt.id, tipo: 'update', medicalRecordId: this.state.medicalRecordId}}>Visualizar Registro</Link></td>
                                        </tr>                                   
                                    ); 
                                })}                                           
                            </tbody>
                        </table>   
                    </div>
                    }
                    <div className="row">
                        <div className="col-md-3">
                            <Link to={{pathname: "/anamnesis", id: 0, tipo: 'create', medicalRecordId: this.state.medicalRecordId}}><input className="form-button" type="button" value="Incluir novo registro" /></Link>                                                                            
                        </div>
                    </div>  
                </div>                                    
            </>
        );
    }
}

export default AnamnesisList;