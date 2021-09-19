import React from 'react';
import './signInForm.css';
import { Redirect } from 'react-router-dom';
import axios from 'axios';
import Cookies from 'js-cookie';
//import APIServices from '../../Services';

class SignInForm extends React.Component{
    constructor(){
        super();
        this.state={        
            redirect: false,
            account: {username: "", password: ""},
            errors: {}
        }
        this.handleLoginUser = this.handleLoginUser.bind(this);
    }

    validate = () => {
        const errors = {};
        const { account } = this.state;

        if (account.username.trim() === ''){
            errors.username = 'O campo usuário é obrigatório.'
        }
        if (account.password.trim() === ''){
            errors.password = 'O campo senha é obrigatório.'
        }

        return Object.keys(errors).length === 0 ? null : errors;
    };

    handleLoginUser = e =>{
        /*e.preventDefault();
        const redirectCallback = new APIServices().loginUser(this.state.account.username, this.state.account.password);
        if (!redirectCallback.value){
            const errors = {};
            errors.invalidCredentials = 'Usuário e/ou senha inválidos.'
            this.setState({errors: errors});
            console.log('Error Debug: ', redirectCallback.value);
            return;    
        }
        this.setState({redirect: redirectCallback.value});*/
        e.preventDefault();

        const errors = this.validate();
        this.setState({errors: errors || {}});

        if (errors) return;

        let that = this;
        let bodyFormData = new FormData();

        bodyFormData.append('username', this.state.account.username);
        bodyFormData.append('password', this.state.account.password);
        bodyFormData.append('grant_type', 'password');

        axios({
            method: 'post',
            url: 'http://localhost:8080/oauth/token',
            data: bodyFormData,
            headers: { "Authorization" : "Basic Y2xpZW50OjEyMw==" } 
        })
        .then(function (response) { 
            if (response.status === 200){           
                Cookies.set('token', response.data['access_token']);
                that.setState({
                    redirect: true
                });
            }                  
        })
        .catch(function (error) {
            const errors = {};
            errors.invalidCredentials = 'Usuário e/ou senha inválidos.'
            that.setState({errors: errors});
            console.log('Error Debug: ', error);
        });
    }

    handleChange = ({currentTarget: input}) =>{        
        const account = {...this.state.account};        
        account[input.name] = input.value;
        this.setState({ account });
    }

    render(){
        const { account } = this.state;
        if (this.state.redirect) {
            return <Redirect to="/home" />
        }
        return (            
            <>
                <div className="row d-flex justify-content-center">
                    <div className="col-xs-5 col-md-3 col-sm-7 text-center form-box">
                        <p className="form-title">Entrar</p>
                        <p className="form-subtitle">Informe seu e-mail e senha para entrar.</p>
                        <form onSubmit={this.handleLoginUser}>
                            {this.state.errors['invalidCredentials'] && <div className="alert alert-danger">{this.state.errors['invalidCredentials']}</div>}
                            <input value={account.username} name="username" id="username" onChange={this.handleChange} className="form-input" type="text" placeholder="E-mail" />
                            {this.state.errors['username'] && <div className="alert alert-danger">{this.state.errors['username']}</div>}
                            <input value={account.password} name="password" id="password" onChange={this.handleChange} className="form-input" type="password" placeholder="Senha" />
                            {this.state.errors['password'] && <div className="alert alert-danger">{this.state.errors['password']}</div>}
                            <input className="formu-button" type="submit" value="Entrar"/>
                        </form>
                    </div>
                </div>
            </>
        );
    }
}

export default SignInForm;