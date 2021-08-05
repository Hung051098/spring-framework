package com.hung.security.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class ApiRest {
	
	@GetMapping("/hung")
	public String rest() {
		return "ApiRest ...";
	}

}
