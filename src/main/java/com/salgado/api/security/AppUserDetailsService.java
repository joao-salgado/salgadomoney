package com.salgado.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salgado.api.model.UserAccount;
import com.salgado.api.repository.UserAccountRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserAccount> user = userAccountRepository.findByEmail(email);
		UserAccount userAccount = user.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new User(email, userAccount.getPassword(), getPermissions(userAccount));
	}

	private Collection<? extends GrantedAuthority> getPermissions(UserAccount userAccount) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		userAccount.getPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
		return authorities;
	}

}
