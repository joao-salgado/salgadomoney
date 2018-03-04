package com.salgado.api.repository.person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.salgado.api.model.Person;
import com.salgado.api.repository.filter.PersonFilter;

public interface PersonRepositoryQuery {
	public Page<Person> filter(PersonFilter personFilter, Pageable pageable);
}
