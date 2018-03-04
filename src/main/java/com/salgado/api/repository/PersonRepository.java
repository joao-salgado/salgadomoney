package com.salgado.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salgado.api.model.Person;
import com.salgado.api.repository.person.PersonRepositoryQuery;

public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryQuery {

}
