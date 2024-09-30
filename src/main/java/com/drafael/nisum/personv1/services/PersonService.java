package com.drafael.nisum.personv1.services;

import com.drafael.nisum.personv1.dto.request.PersonLoginRequest;
import com.drafael.nisum.personv1.dto.request.PersonRequest;
import com.drafael.nisum.personv1.dto.response.PersonLoginResponse;
import com.drafael.nisum.personv1.dto.response.PersonResponse;
import com.drafael.nisum.personv1.dto.response.PersonResponseItemList;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface PersonService {
    Single<PersonResponse> registerPerson(PersonRequest request);
    Single<Boolean> deletePerson(String personCellphone);
    Flowable<PersonResponseItemList> listPeople();

    Single<PersonLoginResponse> login(PersonLoginRequest request);
}
