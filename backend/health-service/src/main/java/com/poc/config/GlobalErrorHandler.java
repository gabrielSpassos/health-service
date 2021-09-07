package com.poc.config;

import com.poc.dto.ErrorDTO;
import com.poc.exception.BaseHttpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {

    private static final String VALIDATION_INPUT_CODE = "997";
    private static final String ACCESS_DENIED_CODE = "998";
    private static final String ACCESS_DENIED_MESSAGE = "Seu usuário não tem permissão para executar esta tarefa";
    private static final String DEFAULT_CODE = "999";
    private static final String DEFAULT_MESSAGE = "Sistema indisponível";

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception exception) {
        if (exception instanceof BaseHttpException) {
            BaseHttpException baseHttpException = (BaseHttpException) exception;
            log.error("Erro tratado", baseHttpException);
            return createResponseError(baseHttpException.getHttpStatus(), baseHttpException.getErrorDTO());
        }

        log.error("Erro não tratado", exception);
        ErrorDTO errorDTO = new ErrorDTO(DEFAULT_CODE, DEFAULT_MESSAGE);
        return createResponseError(HttpStatus.INTERNAL_SERVER_ERROR, errorDTO);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ErrorDTO> handleAccessDeniedException(AccessDeniedException accessDeniedException) {
        log.error("Erro tratado", accessDeniedException);
        ErrorDTO errorDTO = new ErrorDTO(ACCESS_DENIED_CODE, ACCESS_DENIED_MESSAGE);
        return createResponseError(HttpStatus.FORBIDDEN, errorDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO>  handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException) {
        String errorMessage = methodArgumentNotValidException.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(methodArgumentNotValidException.getMessage());

        ErrorDTO errorDTO = new ErrorDTO(VALIDATION_INPUT_CODE, errorMessage);
        return createResponseError(HttpStatus.BAD_REQUEST, errorDTO);
    }

    private ResponseEntity<ErrorDTO> createResponseError(HttpStatus httpStatus, ErrorDTO errorDTO) {
        log.error("Error status: {}, body: {}", httpStatus, errorDTO);
        return ResponseEntity.status(httpStatus).body(errorDTO);
    }
}
