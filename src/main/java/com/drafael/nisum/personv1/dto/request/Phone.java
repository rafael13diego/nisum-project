package com.drafael.nisum.personv1.dto.request;


import lombok.Data;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Entity
@Data
public class Phone {

    @Id
    private String id = UUID.randomUUID().toString();
    @NotBlank @Pattern(regexp = "9\\d{8}")
    private String number;
    @NotBlank @Pattern(regexp = "^\\d{1,2}$")
    private String cityCode;
    @NotBlank @Pattern(regexp = "^\\d{1,2}$")
    private String countryCode;
}
