package com.hung.security.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRest {
	
	@GetMapping("/hung")
	public String rest() {
		return "UserRest ...";
	}

}
