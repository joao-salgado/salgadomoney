package com.salgado.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salgado.api.model.Entry;
import com.salgado.api.model.Person;
import com.salgado.api.repository.EntryRepository;
import com.salgado.api.repository.PersonRepository;
import com.salgado.api.service.exception.PersonNotExistOrInactiveException;

@Service
public class EntryService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private EntryRepository entryRepository;

	public Entry save(Entry entry) {
		
		Person person = personRepository.findOne(entry.getPerson().getId());
		
		if(person == null || !person.isActive()) {
			throw new PersonNotExistOrInactiveException();
		}
		
		return entryRepository.save(entry);
	}

}
