package com.hung.security.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hung.security.entity.Authority;
import com.hung.security.entity.Customer;
import com.hung.security.repository.CustomerRepository;

@Component
public class SecurityUsernamePwdAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public boolean supports(Class<?> authenticationType) {
		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
	}

	@Override
	public org.springframework.security.core.Authentication authenticate(
			org.springframework.security.core.Authentication authentication) throws AuthenticationException {
		try {
			String username = authentication.getName();
			String pwd = authentication.getCredentials().toString();
			Customer customer = customerRepository.findByEmail(username);
			if (customer != null) {
				if (passwordEncoder.matches(pwd, customer.getPwd())) {
					List<GrantedAuthority> authorities = new ArrayList<>();
					authorities.add(new SimpleGrantedAuthority(customer.getRole()));
					return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(customer.getAuthorities()));
				} else {
					throw new BadCredentialsException("Invalid password!");
				}
			}else {
				throw new BadCredentialsException("No user registered with this details!");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> lstAuthorities) {
		List<GrantedAuthority> grantedauthorities = new ArrayList<>();
		for (Authority authorities : lstAuthorities) {
			grantedauthorities.add(new SimpleGrantedAuthority(authorities.getName()));
		}
		return grantedauthorities;
	}
}
