package com.example.springsecurityjwt.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRest {

	@GetMapping("/hello")
	public String hello()
	{
		return "hello";
	}
}