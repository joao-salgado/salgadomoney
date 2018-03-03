package com.salgado.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.salgado.api.event.ResourceCreatedEvent;
import com.salgado.api.exceptionhandler.SalgadoMoneyExceptionHandler.Error;
import com.salgado.api.model.Entry;
import com.salgado.api.repository.EntryRepository;
import com.salgado.api.repository.filter.EntryFilter;
import com.salgado.api.repository.projection.EntryResume;
import com.salgado.api.service.EntryService;
import com.salgado.api.service.exception.PersonNotExistOrInactiveException;

@RestController
@RequestMapping("/entries")
public class EntryResource {
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private EntryService entryService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<Entry> search(EntryFilter entryFilter, Pageable pageable) {
		return entryRepository.filter(entryFilter, pageable);
	}
	
	@GetMapping(params="resume")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<EntryResume> resume(EntryFilter entryFilter, Pageable pageable) {
		return entryRepository.resume(entryFilter, pageable);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Entry entry = entryRepository.findOne(id);
		return entry != null ? ResponseEntity.ok(entry) : ResponseEntity.notFound().build() ;
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Entry> toCreate(@Valid @RequestBody Entry entry, HttpServletResponse response) {
		Entry savedEntry = entryService.save(entry);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, savedEntry.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntry);
	}
	
	@ExceptionHandler({PersonNotExistOrInactiveException.class})
	public ResponseEntity<Object> handlePersonNotExistOrInactiveException(PersonNotExistOrInactiveException ex) {
		
		String msgUser = messageSource.getMessage("person.not-exist-or-inactive", null, LocaleContextHolder.getLocale());
		String msgProgrammer = ex.toString();
		List<Error> errors = Arrays.asList(new Error(msgUser, msgProgrammer));
		return ResponseEntity.badRequest().body(errors);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void toRemove(@PathVariable Long id) {
		entryRepository.delete(id);
	}
	

}
