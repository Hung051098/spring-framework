package com.example.springsecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springsecurityjwt.model.Customer;


@Repository
public interface CustomerRepositoty extends JpaRepository<Customer, Integer>{
	@Query(value = "select c from Customer c where c.email = ?1")
	Customer findByEmail(String email);
}
