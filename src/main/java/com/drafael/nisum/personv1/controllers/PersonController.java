package com.drafael.nisum.personv1.controllers;

import com.drafael.nisum.personv1.dto.request.PersonLoginRequest;
import com.drafael.nisum.personv1.dto.request.PersonRequest;
import com.drafael.nisum.personv1.dto.response.PersonLoginResponse;
import com.drafael.nisum.personv1.dto.response.PersonResponse;
import com.drafael.nisum.personv1.dto.response.PersonResponseItemList;
import com.drafael.nisum.personv1.services.PersonService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/person-v1/")
@RequiredArgsConstructor
@Slf4j
public class PersonController {

    private final PersonService service;
    @PostMapping("sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Single<PersonResponse> registryPerson(@Valid @RequestBody PersonRequest request) {

        return service.registerPerson(request);
    }

    @GetMapping("list-people")
    @ResponseStatus(HttpStatus.OK)
    public Flowable<PersonResponseItemList> listPeople() {

        return service.listPeople();
    }

    @DeleteMapping("delete/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Single<Boolean> logicalPersonDelete(
            @Valid @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
            @PathVariable String email) {

        return service.deletePerson(email);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public Single<PersonLoginResponse> login(@Valid  @RequestBody PersonLoginRequest request) {
        return service.login(request);
    }
}
