package com.salgado.api.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.salgado.api.model.TypeEntry;

public class EntryResume {

	private Long id;
	private String description;
	private LocalDate dueDate;
	private LocalDate datePayment;
	private BigDecimal value;
	private TypeEntry type;
	private String category;
	private String person;
	
	public EntryResume(Long id, String description, LocalDate dueDate, LocalDate datePayment, BigDecimal value,
			TypeEntry type, String category, String person) {
		super();
		this.id = id;
		this.description = description;
		this.dueDate = dueDate;
		this.datePayment = datePayment;
		this.value = value;
		this.type = type;
		this.category = category;
		this.person = person;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	public LocalDate getDatePayment() {
		return datePayment;
	}
	
	public void setDatePayment(LocalDate datePayment) {
		this.datePayment = datePayment;
	}
	
	public BigDecimal getValue() {
		return value;
	}
	
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public TypeEntry getType() {
		return type;
	}
	
	public void setType(TypeEntry type) {
		this.type = type;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getPerson() {
		return person;
	}
	
	public void setPerson(String person) {
		this.person = person;
	}
	
}
