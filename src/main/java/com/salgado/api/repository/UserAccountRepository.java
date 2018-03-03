package com.salgado.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salgado.api.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{

	public Optional<UserAccount> findByEmail(String email);
	
}
