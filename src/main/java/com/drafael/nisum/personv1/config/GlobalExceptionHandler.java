package com.drafael.nisum.personv1.config;


import com.drafael.nisum.personv1.dto.exception.ExceptionApiMain;
import com.drafael.nisum.personv1.dto.exception.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static com.drafael.nisum.personv1.utils.Constans.ERROR_BAD_REQUEST;
import static com.drafael.nisum.personv1.utils.Constans.ERROR_BAD_REQUEST_MESSAGE;

@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleCustomWebExchangeBindException(MethodArgumentNotValidException ex) {

        var exceptionBody = new ExceptionResponse();

        exceptionBody.setErrorCode(ERROR_BAD_REQUEST);
        exceptionBody.setErrorDescription(ERROR_BAD_REQUEST_MESSAGE);
        exceptionBody.setErrorDetails(ex.getFieldErrors().stream()
                .collect(Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList()))));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionBody);

    }

    @ExceptionHandler(ExceptionApiMain.class)
    public ResponseEntity<ExceptionResponse> handleApiMainException(ExceptionApiMain ex) {

        var exceptionBody = new ExceptionResponse();
        exceptionBody.setErrorCode(ex.getCodeError());
        exceptionBody.setErrorDescription(ex.getException());

        return ResponseEntity.status(ex.getHttpCode()).body(exceptionBody);

    }
}