package com.drafael.nisum.personv1.dto.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionApiMain extends RuntimeException{
    private String exception;
    private String codeError;
    private Integer httpCode = 500;
    private Map<String, List<String>> errorDetails;

    public ExceptionApiMain(String exception, String codeError) {
        this.exception = exception;
        this.codeError = codeError;
    }

    public ExceptionApiMain(String exception, String codeError, Integer httpCode) {
        this.exception = exception;
        this.codeError = codeError;
        this.httpCode =  httpCode;
    }
}
