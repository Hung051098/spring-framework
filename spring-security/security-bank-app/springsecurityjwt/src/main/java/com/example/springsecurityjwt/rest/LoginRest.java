package com.example.springsecurityjwt.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurityjwt.request.AuthenticationLoginRequest;
import com.example.springsecurityjwt.response.AuthenticationLoginResponse;
import com.example.springsecurityjwt.util.JwtToken;

@RestController
public class LoginRest {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService securityLoginService;
	
	@Autowired
	JwtToken jwtToken;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationLoginRequest request) throws Exception
	{
		try {
			List<GrantedAuthority> Authorities = new ArrayList<>();
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword(), Authorities));
		} catch (Exception e) {
			throw new Exception("Username or Password incorect");
		}
		final UserDetails userDetails = securityLoginService.loadUserByUsername(request.getUsername());
		String token = jwtToken.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationLoginResponse(token));
		
	}
}
