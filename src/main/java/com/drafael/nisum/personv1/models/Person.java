package com.drafael.nisum.personv1.models;

import com.drafael.nisum.personv1.dto.request.Phone;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;


@Entity
@Data
public class Person {

    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String email;
    private String password;
    private String token;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "person_id")
    private List<Phone> phones;
    private boolean isActive;
    private String createdAt;
    private String updatedAt;
    private String lastLogin;

}
