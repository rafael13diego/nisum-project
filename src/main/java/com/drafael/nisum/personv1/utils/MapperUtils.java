package com.drafael.nisum.personv1.utils;


import com.drafael.nisum.personv1.dto.request.PersonRequest;
import com.drafael.nisum.personv1.dto.response.PersonLoginResponse;
import com.drafael.nisum.personv1.dto.response.PersonResponse;
import com.drafael.nisum.personv1.dto.response.PersonResponseItemList;
import com.drafael.nisum.personv1.models.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;



import static com.drafael.nisum.personv1.utils.Constans.WELCOME_LOGIN_RESPONSE;

@Component
@RequiredArgsConstructor
public class MapperUtils {

    private final ModelMapper modelMapper;
    private final JwtUtils jwtUtils;

    public Person mapRequestToPerson(PersonRequest request) {

        String date = GeneralUtils.getDateInLimaTimeZone();
        Person person = modelMapper.map(request, Person.class);
        person.setPassword(GeneralUtils.encodePassword(request.getPassword()));
        person.setCreatedAt(date);
        person.setUpdatedAt(date);
        person.setLastLogin(date);
        person.setToken(jwtUtils.generateToken(person.getId()));
        person.setActive(true);

        return person;
    }


    public PersonResponse mapPersonToResponse(Person person) {

        return  modelMapper.map(person, PersonResponse.class);
    }

    public PersonResponseItemList mapPersonToItemList(Person person) {

        PersonResponseItemList itemList = modelMapper.map(person, PersonResponseItemList.class);
        return  itemList;
    }

    public PersonLoginResponse mapPersonToLoginResponse(Person personLogin) {
        var responseLogin = new PersonLoginResponse();
        responseLogin.setMessage(WELCOME_LOGIN_RESPONSE);
        responseLogin.setToken(personLogin.getToken());
        return responseLogin;
    }
}
