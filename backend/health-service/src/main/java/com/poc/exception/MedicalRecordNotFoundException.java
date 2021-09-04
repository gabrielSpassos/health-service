package com.poc.exception;

import org.springframework.http.HttpStatus;

public class MedicalRecordNotFoundException extends BaseHttpException{

    public MedicalRecordNotFoundException() {
        super("3", "Prontuário não encontrado", HttpStatus.NOT_FOUND);
    }
}
