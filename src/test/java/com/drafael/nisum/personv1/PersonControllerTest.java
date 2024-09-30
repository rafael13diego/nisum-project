package com.drafael.nisum.personv1;

import com.drafael.nisum.personv1.controllers.PersonController;
import com.drafael.nisum.personv1.dto.request.PersonLoginRequest;
import com.drafael.nisum.personv1.dto.request.PersonRequest;
import com.drafael.nisum.personv1.dto.response.PersonLoginResponse;
import com.drafael.nisum.personv1.dto.response.PersonResponse;
import com.drafael.nisum.personv1.dto.response.PersonResponseItemList;
import com.drafael.nisum.personv1.services.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @MockBean
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegistryPerson() throws Exception {
        //Arrange
        PersonRequest personRequest = new PersonRequest();
        personRequest.setName("Diego");
        personRequest.setEmail("maradona@gmail.com");
        personRequest.setPassword("123Diego$");
        personRequest.setPhones(anyList());
        PersonResponse personResponse = new PersonResponse();
        //Act
        when(personService.registerPerson(personRequest)).thenReturn(Single.just(personResponse));
        //Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/apis/person-v1/sign-up")
                        .content(asJsonString(personRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testListPeople() throws Exception {
        PersonResponseItemList personResponseItemList = new PersonResponseItemList();

        when(personService.listPeople()).thenReturn(Flowable.just(personResponseItemList));

        mockMvc.perform(MockMvcRequestBuilders.get("/apis/person-v1/list-people")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testLogicalPersonDelete() throws Exception {
        String personEmail = "maradona@gmail.com";

        when(personService.deletePerson(personEmail)).thenReturn(Single.just(true));

        mockMvc.perform(MockMvcRequestBuilders.delete("/apis/person-v1/delete/" + personEmail)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testLogin() throws Exception {
        PersonLoginRequest requestLogin = new PersonLoginRequest();
        requestLogin.setEmail("maradona@gmail.com");
        requestLogin.setPassword("123Diego$");
        PersonLoginResponse personResponse = new PersonLoginResponse();

        when(personService.login(requestLogin)).thenReturn(Single.just(personResponse));

        mockMvc.perform(MockMvcRequestBuilders.post("/apis/person-v1/login")
                        .content(asJsonString(requestLogin))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}