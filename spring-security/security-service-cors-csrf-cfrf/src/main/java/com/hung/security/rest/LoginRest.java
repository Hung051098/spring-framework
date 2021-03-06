package com.hung.security.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hung.security.entity.Customer;
import com.hung.security.repository.CustomerRepository;

@RestController
@RequestMapping("/user")
public class LoginRest {

	@Autowired
	CustomerRepository customerRepository;

	@RequestMapping("/login")
	public Customer getUserDetailsAfterLogin(Principal user) {
		Customer customers = customerRepository.findByEmail(user.getName());
		if (customers != null) {
			return customers;
		} else {
			return null;
		}
	}
}
