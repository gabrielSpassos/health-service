import React from 'react';

class SearchPatient extends React.Component{
    render(){
        return (
            <>
                <div className="col-8">                    
                    <div className="row d-flex justify-content-start">
                        <div className="form-box">
                            <p className="">Cadastro de institucionalizado</p>
                            <form>
                                <div className="row d-flex">
                                    <div className="col">
                                        <input type="text" name="" id="" onChage="" value="" className="form-input" placeholder="Nome completo" />
                                        <input type="text" name="" id="" onChage="" value="" className="form-input" placeholder="RG" />
                                        <input type="text" name="" id="" onChage="" value="" className="form-input" placeholder="CPF" />    
                                    </div>
                                </div>                                                            
                                <input type="text" name="" id="" onChage="" value="" className="form-input" placeholder="Data de nascimento" />
                                <input type="text" name="" id="" onChage="" value="" className="form-input" placeholder="Telefone de contato" />
                                <input type="text" name="" id="" onChage="" value="" className="form-input" placeholder="Sexo" />
                                <select name="sexo" className="form-input">
                                    <option value="" defaultChecked>Selecionar</option>
                                    <option value="M">Masculino</option>
                                    <option value="F">Feminino</option>
                                </select>                            
                                <input className="form-button" type="submit" value="Cadastrar" />
                            </form>
                        </div>
                    </div>
                </div>              
            </>
        );
    }
}

export default SearchPatient;