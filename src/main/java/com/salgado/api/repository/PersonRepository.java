package com.salgado.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salgado.api.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
