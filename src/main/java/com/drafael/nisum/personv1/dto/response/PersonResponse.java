package com.drafael.nisum.personv1.dto.response;

import lombok.Data;

@Data
public class PersonResponse {

    private String id;
    private String name;
    private String createdAt;
    private String updatedAt;
    private String lastLogin;
    private String token;
    private boolean isActive;
}
