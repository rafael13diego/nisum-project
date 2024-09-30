package com.drafael.nisum.personv1.dto.response;

import com.drafael.nisum.personv1.dto.request.Phone;
import lombok.Data;

import java.util.List;

@Data
public class PersonResponseItemList {

    private String name;
    private String email;
    private String password;
    private String updatedAt;
    private String lastLogin;
    private String token;
    private List<Phone> phones;
    private boolean isActive;
}
