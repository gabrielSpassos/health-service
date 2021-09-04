package com.poc.exception;

import com.poc.dto.ErrorDTO;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public abstract class BaseHttpException extends RuntimeException {

    private final ErrorDTO errorDTO;
    private final HttpStatus httpStatus;

    public BaseHttpException(String code, String message, HttpStatus httpStatus) {
        this.errorDTO = new ErrorDTO(code, message);
        this.httpStatus = httpStatus;
    }
}
