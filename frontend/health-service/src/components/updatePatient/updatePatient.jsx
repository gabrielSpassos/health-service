import React from 'react';
import './updatePatient.css';
import axios from 'axios';
import Cookies from 'js-cookie';
import NavBar from './../navBar/navBar';
import VerticalNavBar from './../verticalNavBar/verticalNavBar';
import General from './../general/general';
import Anamnesis from '../anamnesis/anamnesis';
import AnamnesisList from '../anamnesis-list/anamnesis-list';

class UpdatePatient extends React.Component{
    constructor(props){
        super(props);
        this.state={
            patient: {name: "Matheus da Rosa Pinheiro", cpf: "00000000000", rg: "00000000", sex: "MALE", phone: "51999334183", birthdate: "29/11/1994", id: "1"},        
            patients: [{name: "", cpf: "", rg: "", sex: "", phone: "", birthdate: ""}],
            errors: {},
            activeTab: {tabName: "general"}
        }
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit = e =>{
        e.preventDefault();
        
        /*let that = this;
        const token = 'Bearer ' + Cookies.get('token');

        axios({
            method: 'get',
            url: 'http://localhost:8080/v1/patients',
            headers: { "Authorization" : token } 
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
        });*/
    }

    handleTabChange = ({currentTarget: tab}) =>{
        let activeTab = this.state.activeTab;

        if (tab.name === "general"){
            activeTab.tabName = "general";      
        }else{
            activeTab.tabName = "anamnesis";
        }

        this.setState({ activeTab });  
    }

    render(){
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
                                    <ul className="nav nav-tabs">
                                        <li className="nav-item">
                                            <a className={this.state.activeTab.tabName === "general" ? 'nav-link active' : 'nav-link'} name="general" aria-current="page" onClick={this.handleTabChange}>Informações gerais</a>
                                        </li>
                                        <li className="nav-item">
                                            <a className={this.state.activeTab.tabName === "anamnesis" ? 'nav-link active' : 'nav-link'} name="anamnesis" onClick={this.handleTabChange}>Anamnese</a>
                                        </li>
                                        <li className="nav-item">
                                            <a className="nav-link">Monitoramento</a>
                                        </li>
                                    </ul>
                                </div>                                
                            </div>
                            <div className="row d-flex justify-content-start">
                                {this.state.activeTab.tabName === "anamnesis" && <AnamnesisList id={this.props.location.id} />}
                                {this.state.activeTab.tabName === "general" && <General id={this.props.location.id} />}
                            </div>
                        </div>
                    </div>
                </div>          
            </>
        );
    }
}

export default UpdatePatient;