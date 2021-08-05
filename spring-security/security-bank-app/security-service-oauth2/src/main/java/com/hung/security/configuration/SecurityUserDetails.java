//package com.hung.security.configuration;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.hung.security.entity.Customer;
//import com.hung.security.entity.security.SecurityCustomer;
//import com.hung.security.repository.CustomerRepository;
//
//@Service
//public class SecurityUserDetails implements UserDetailsService  {
//
//	@Autowired
//	private CustomerRepository customerRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Customer customer = customerRepository.findByEmail(username);
//		if (customer == null) {
//			throw new UsernameNotFoundException("User details not found for the user : " + username);
//		}
//		return new SecurityCustomer(customer);
//	}
//
//	
//
//}
