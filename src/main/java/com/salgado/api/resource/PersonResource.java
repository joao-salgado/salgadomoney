package com.salgado.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salgado.api.event.ResourceCreatedEvent;
import com.salgado.api.model.Person;
import com.salgado.api.repository.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonResource {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Person> toList() {
		return personRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Person> toCreate(@Valid @RequestBody Person person, HttpServletResponse response) {
		Person savedPerson = personRepository.save(person);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, savedPerson.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Person person = personRepository.findOne(id);
		return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
		
	}
	
}
