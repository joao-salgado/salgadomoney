package com.salgado.api.repository.entry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.salgado.api.model.Entry;
import com.salgado.api.repository.filter.EntryFilter;
import com.salgado.api.repository.projection.EntryResume;

public interface EntryRepositoryQuery {

	public Page<Entry> filter(EntryFilter entryFilter, Pageable pageable);
	public Page<EntryResume> resume(EntryFilter entryFilter, Pageable pageable);
	
}
