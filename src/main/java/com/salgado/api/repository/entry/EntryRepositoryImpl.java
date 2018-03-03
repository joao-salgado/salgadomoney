package com.salgado.api.repository.entry;

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

import com.salgado.api.model.Entry;
import com.salgado.api.repository.filter.EntryFilter;
import com.salgado.api.repository.projection.EntryResume;

public class EntryRepositoryImpl implements EntryRepositoryQuery {

	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Entry> filter(EntryFilter entryFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Entry> criteria = builder.createQuery(Entry.class);
		Root<Entry> root = criteria.from(Entry.class);
		
		Predicate[] predicates = createRestrictions(entryFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Entry> query = manager.createQuery(criteria);
		
		addPaginationRestrictions(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(entryFilter));
		
	}
	
	@Override
	public Page<EntryResume> resume(EntryFilter entryFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder(); 
		CriteriaQuery<EntryResume> criteria = builder.createQuery(EntryResume.class);
		Root<Entry> root = criteria.from(Entry.class);
		
		criteria.select(builder.construct(EntryResume.class
				, root.get("id"), root.get("description")
				, root.get("dueDate"), root.get("datePayment")
				, root.get("value"), root.get("type")
				, root.get("category").get("name")
				, root.get("person").get("name")));
		
		Predicate[] predicates = createRestrictions(entryFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<EntryResume> query = manager.createQuery(criteria);
		
		addPaginationRestrictions(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(entryFilter));
		
	}

	private Predicate[] createRestrictions(EntryFilter entryFilter, CriteriaBuilder builder, Root<Entry> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(entryFilter.getDescription())) {
			predicates.add(builder.like(
					builder.lower(root.get("description")), "%" + entryFilter.getDescription().toLowerCase() + "%"));
		}
		
		if(entryFilter.getDueDateFrom() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dueDate"), entryFilter.getDueDateFrom()));
		}
		
		if(entryFilter.getDueDateUntil() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dueDate"), entryFilter.getDueDateUntil()));
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
	
	private Long total(EntryFilter entryFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Entry> root = criteria.from(Entry.class);
		
		Predicate[] predicates = createRestrictions(entryFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
