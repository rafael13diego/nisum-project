package com.drafael.nisum.personv1.utils;


import com.drafael.nisum.personv1.dto.exception.ExceptionApiMain;
import org.springframework.stereotype.Component;

@Component
public class ExceptionUtils {

    public ExceptionApiMain buildMainException(String exception, String codeError) {

        return new ExceptionApiMain(exception,codeError);
    }

    public ExceptionApiMain buildMainException(String exception, String codeError, Integer httpCode) {

        return new ExceptionApiMain(exception,codeError, httpCode);
    }

}