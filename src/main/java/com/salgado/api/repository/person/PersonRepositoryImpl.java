package com.salgado.api.repository.person;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.salgado.api.model.Person;
import com.salgado.api.repository.filter.PersonFilter;

public class PersonRepositoryImpl implements PersonRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Person> filter(PersonFilter personFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
		Root<Person> root = criteria.from(Person.class);
		
		Predicate[] predicates = createRestrictions(personFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Person> query = manager.createQuery(criteria);
		
		addPaginationRestrictions(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(personFilter));
		
	}
	
	private Predicate[] createRestrictions(PersonFilter personFilter, CriteriaBuilder builder, Root<Person> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(personFilter.getName())) {
			predicates.add(builder.like(
					builder.lower(root.get("name")), "%" + personFilter.getName().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void addPaginationRestrictions(TypedQuery<?> query, Pageable pageable) {
		
		int currentPage = pageable.getPageNumber();
		int totalRecordsForPage = pageable.getPageSize();
		int firstRecordOfPage = currentPage * totalRecordsForPage;
		
		query.setFirstResult(firstRecordOfPage);
		query.setMaxResults(totalRecordsForPage);
	}
	
	private Long total(PersonFilter personFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Person> root = criteria.from(Person.class);
		
		Predicate[] predicates = createRestrictions(personFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
