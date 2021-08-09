package com.example.authprovider.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppProvider implements AuthenticationProvider , Serializable{

	
	@Autowired
	private PasswordEncoder passwordEncoder;

	// chú ý chỗ này quan trọng
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		if (passwordEncoder.matches("123456", password)) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			return new UsernamePasswordAuthenticationToken("hung", "123456", authorities);
		} else {
			throw new BadCredentialsException("Invalid password!");
		}
	}

}
