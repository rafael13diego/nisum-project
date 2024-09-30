package com.drafael.nisum.personv1.services.PersonServiceImpl;

import com.drafael.nisum.personv1.dto.request.PersonLoginRequest;
import com.drafael.nisum.personv1.dto.request.PersonRequest;
import com.drafael.nisum.personv1.dto.response.PersonLoginResponse;
import com.drafael.nisum.personv1.dto.response.PersonResponse;
import com.drafael.nisum.personv1.dto.response.PersonResponseItemList;
import com.drafael.nisum.personv1.models.Person;
import com.drafael.nisum.personv1.repositories.PersonRepository;
import com.drafael.nisum.personv1.services.PersonService;
import com.drafael.nisum.personv1.utils.ExceptionUtils;
import com.drafael.nisum.personv1.utils.JwtUtils;
import com.drafael.nisum.personv1.utils.MapperUtils;
import com.drafael.nisum.personv1.utils.GeneralUtils;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.drafael.nisum.personv1.utils.Constans.*;
import static io.reactivex.rxjava3.core.Single.just;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final MapperUtils mapperUtils;
    private final ExceptionUtils exUtils;
    private final JwtUtils jwtUtils;
    @Override
    public Single<PersonResponse> registerPerson(PersonRequest request) {

        return  just(repository.existsByEmailAndIsActive(request.getEmail(), true))
                        .flatMap(existsPerson -> !existsPerson ?
                                just(mapperUtils.mapRequestToPerson(request))
                                        .map(repository::save)
                                        .map(mapperUtils::mapPersonToResponse)
                                        .doOnError(error -> log.error(error.getMessage() + " " + error.getCause()))
                                .onErrorResumeNext(error -> Single.error(exUtils.buildMainException(ERROR_DATABASE_MESSAGE, ERROR_DATABASE))) :
                                Single.error(exUtils.buildMainException(ERROR_EXISTS_PERSON_MESSAGE, ERROR_EXISTS_PERSON, 400)));
    }

    @Override
    public Single<Boolean> deletePerson(String email) {
        return just(repository.existsByEmailAndIsActive(email, false))
                .flatMap(isPersonExists -> isPersonExists ?
                        Single.error(exUtils.buildMainException(ERROR_NOT_EXISTS_PERSON_MESSAGE, ERROR_NOT_EXISTS_PERSON)) :
                        just(repository.updateIsActiveByEmail(false, email))
                )
                .map(affected -> affected > 0)
                .onErrorResumeNext(error -> Single.error(exUtils.buildMainException("Error deleting person", "DELETE_PERSON_FAILED")));
    }



    @Override
    public Flowable<PersonResponseItemList> listPeople() {
        return Flowable.fromIterable(repository.findAll())
                .filter(Person::isActive)
                .map(mapperUtils::mapPersonToItemList);
    }

    @Override
    public Single<PersonLoginResponse> login(PersonLoginRequest request) {

        return Single.fromCallable(() -> {
            Person existingPerson = repository.findByEmailAndPasswordAndIsActive(
                    request.getEmail(), GeneralUtils.encodePassword(request.getPassword()), true);

            if (existingPerson == null) {
                throw exUtils.buildMainException(ERROR_LOGIN_MESSAGE, ERROR_LOGIN, 401);
            }

            existingPerson.setLastLogin(GeneralUtils.getDateInLimaTimeZone());
            existingPerson.setToken(jwtUtils.generateToken(existingPerson.getId()));

            return repository.save(existingPerson);
        }).map(mapperUtils::mapPersonToLoginResponse);
    }


}