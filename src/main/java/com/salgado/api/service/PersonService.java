package com.salgado.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.salgado.api.model.Person;
import com.salgado.api.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;

	public Person update(Long id, Person person) {
		Person savedPerson = findPersonById(id);
		
		BeanUtils.copyProperties(person, savedPerson, "id");
		return personRepository.save(savedPerson);
	}

	public void updatePropertyActive(Long id, Boolean active) {
		Person savedPerson = findPersonById(id);
		savedPerson.setActive(active);
		personRepository.save(savedPerson);
	}
	
	public Person findPersonById(Long id) {
		Person savedPerson = personRepository.findOne(id);
		
		if(savedPerson == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return savedPerson;
	}
	
}
