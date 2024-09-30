package com.drafael.nisum.personv1.dto.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

    private String errorCode;
    private String errorDescription;
    private Map<String, String> responseErrorBody;
    private Map<String, List<String>> errorDetails;

}
