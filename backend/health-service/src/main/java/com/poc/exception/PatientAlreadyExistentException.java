package com.poc.exception;

import org.springframework.http.HttpStatus;

public class PatientAlreadyExistentException extends BaseHttpException{

    public PatientAlreadyExistentException() {
        super("1", "Paciente já cadastrado", HttpStatus.BAD_REQUEST);
    }

}
