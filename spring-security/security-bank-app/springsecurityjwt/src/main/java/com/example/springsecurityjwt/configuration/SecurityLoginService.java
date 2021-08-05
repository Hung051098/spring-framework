package com.example.springsecurityjwt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.springsecurityjwt.model.Customer;
import com.example.springsecurityjwt.model.CustomerUserDetails;
import com.example.springsecurityjwt.repository.CustomerRepositoty;


@Component
public class SecurityLoginService implements UserDetailsService {

	@Autowired
	CustomerRepositoty customerRepositoty;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Customer customer = customerRepositoty.findByEmail(username);
			return new CustomerUserDetails(customer);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new CustomerUserDetails(new Customer());
	}

}
