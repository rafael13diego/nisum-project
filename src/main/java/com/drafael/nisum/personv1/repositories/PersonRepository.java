package com.drafael.nisum.personv1.repositories;

import com.drafael.nisum.personv1.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonRepository extends JpaRepository<Person,String> {

    boolean existsByEmailAndIsActive(String email, boolean isActive);
    Person findByEmailAndPasswordAndIsActive( String email, String password, boolean isActive);
    //Person findByEmailAndPassword( String email, String password);
    @Transactional
    @Modifying
    @Query("UPDATE Person p SET p.isActive = :isActive WHERE p.email = :email")
    int updateIsActiveByEmail(@Param("isActive") boolean isActive, @Param("email") String email);



}
