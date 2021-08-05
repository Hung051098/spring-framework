package com.example.springsecurityjwt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurityjwt.configuration.SecurityLoginService;
import com.example.springsecurityjwt.request.AuthenticationLoginRequest;
import com.example.springsecurityjwt.response.AuthenticationLoginResponse;
import com.example.springsecurityjwt.util.JwtToken;

@RestController
public class LoginRest {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	SecurityLoginService securityLoginService;
	
	@Autowired
	JwtToken jwtToken;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationLoginRequest request) 
	{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		final UserDetails userDetails = securityLoginService.loadUserByUsername(request.getUsername());
		String token = jwtToken.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationLoginResponse(token));
		
	}
}