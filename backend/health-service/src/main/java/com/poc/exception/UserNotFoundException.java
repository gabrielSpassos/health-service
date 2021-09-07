package com.poc.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseHttpException {

    public UserNotFoundException() {
        super("5", "Usuário não encontrado", HttpStatus.NOT_FOUND);
    }
}
