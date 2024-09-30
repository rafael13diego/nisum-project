package com.drafael.nisum.personv1.services;

import com.drafael.nisum.personv1.dto.request.PersonLoginRequest;
import com.drafael.nisum.personv1.dto.request.PersonRequest;
import com.drafael.nisum.personv1.dto.response.PersonLoginResponse;
import com.drafael.nisum.personv1.dto.response.PersonResponse;
import com.drafael.nisum.personv1.dto.response.PersonResponseItemList;
import com.drafael.nisum.personv1.models.Person;
import com.drafael.nisum.personv1.repositories.PersonRepository;
import com.drafael.nisum.personv1.services.PersonServiceImpl.PersonServiceImpl;
import com.drafael.nisum.personv1.utils.JwtUtils;
import com.drafael.nisum.personv1.utils.MapperUtils;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {

    @Mock
    private PersonRepository repository;

    @Mock
    private MapperUtils mapperUtils;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    public void testRegisterPerson() {

        PersonRequest personRequest = new PersonRequest();
        personRequest.setEmail("maradona@gmail.com");
        personRequest.setName("Rafael");

        Person person = new Person();
        PersonResponse personResponse = new PersonResponse();

        when(repository.existsByEmailAndIsActive(anyString(), anyBoolean())).thenReturn(false);
        when(mapperUtils.mapRequestToPerson(any(PersonRequest.class))).thenReturn(person);
        when(repository.save(any(Person.class))).thenReturn(person);
        when(mapperUtils.mapPersonToResponse(any(Person.class))).thenReturn(personResponse);
        
        Single<PersonResponse> result = personService.registerPerson(personRequest);
        
        result.test().assertValue(personResponse);
    }


    @Test
    public void testDeletePerson() {

        String email = "maradona@gmail.com";

        when(repository.existsByEmailAndIsActive(anyString(), anyBoolean())).thenReturn(false);

        Single<Boolean> result = personService.deletePerson(email);

        result.test().assertValue(false);
    }

    @Test
    public void testListPeople() {

        Person person = new Person();
        person.setName("Rafael");
        person.setEmail("maradona@gmail.com");
        person.setActive(true);
        PersonResponseItemList personResponseItemList = new PersonResponseItemList();
        personResponseItemList.setEmail("maradona@gmail.com");
        personResponseItemList.setName("Rafael");

        when(repository.findAll()).thenReturn(List.of(person));
        when(mapperUtils.mapPersonToItemList(any(Person.class))).thenReturn(personResponseItemList);

        Flowable<PersonResponseItemList> result = personService.listPeople();

        result.test().assertValue(personResponseItemList);
    }

    @Test
    public void testLogin() {

        PersonLoginRequest requestLogin = new PersonLoginRequest();
        requestLogin.setEmail("maradona");
        requestLogin.setPassword("12345678");
        PersonLoginResponse responseLogin = new PersonLoginResponse();
        Person person = new Person();
        person.setEmail("maradona");
        person.setPassword("12345678");


        when(repository.findByEmailAndPasswordAndIsActive(anyString(), anyString(), anyBoolean()))
                .thenReturn(person);
        when(jwtUtils.generateToken(anyString())).thenReturn("");
        when(repository.save(any(Person.class))).thenReturn(person);
        when(mapperUtils.mapPersonToLoginResponse(any(Person.class)))
                .thenReturn(responseLogin);

        Single<PersonLoginResponse> result = personService.login(requestLogin);

        result.test()
                .assertValue(responseLogin)
                .assertComplete();
    }


}