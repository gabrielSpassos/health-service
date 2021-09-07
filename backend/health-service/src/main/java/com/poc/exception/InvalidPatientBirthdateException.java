package com.poc.exception;

import org.springframework.http.HttpStatus;

public class InvalidPatientBirthdateException extends BaseHttpException {

    public InvalidPatientBirthdateException() {
        super("6", "Data de nascimento do paciente inválida", HttpStatus.BAD_REQUEST);
    }
}
