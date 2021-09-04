package com.poc.exception;

import org.springframework.http.HttpStatus;

public class RegistryNotFoundException extends BaseHttpException{

    public RegistryNotFoundException() {
        super("4", "Registro não encontrado", HttpStatus.NOT_FOUND);
    }
}
