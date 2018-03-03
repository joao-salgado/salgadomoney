package com.salgado.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salgado.api.model.Entry;
import com.salgado.api.repository.entry.EntryRepositoryQuery;

public interface EntryRepository extends JpaRepository<Entry, Long>, EntryRepositoryQuery {

}
