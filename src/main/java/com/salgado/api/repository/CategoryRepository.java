package com.salgado.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salgado.api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	
	
}
