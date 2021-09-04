package com.poc.exception;

import org.springframework.http.HttpStatus;

public class PatientNotFoundException extends BaseHttpException{

    public PatientNotFoundException() {
        super("2", "Paciente não encontrado", HttpStatus.NOT_FOUND);
    }
}
