import axios from 'axios';
import Cookies from 'js-cookie';

var serviceResponse = {message: '', value: false};

class APIServices {
    loginUser = (username, password) =>{
        let bodyFormData = new FormData();

        bodyFormData.append('username', username);
        bodyFormData.append('password', password);
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
                this.serviceResponse.value = true;
                return this.serviceResponse;
            }                  
        })
        .catch(function (error) {
            serviceResponse.message = 'Usuário e/ou senha inválidos.';
            serviceResponse.value = false;
            console.log('Error Debug: ', error);
        });

        return this.serviceResponse;
    }    
}

export default APIServices;