package com.drafael.nisum.personv1.dto.request;

import lombok.Data;

@Data
public class PersonLoginRequest {

    private String email;
    private String password;
}
