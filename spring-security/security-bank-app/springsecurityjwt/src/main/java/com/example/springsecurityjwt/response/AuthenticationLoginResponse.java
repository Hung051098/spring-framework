package com.example.springsecurityjwt.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationLoginResponse {


	String jwt_token;
//	public AuthenticationLoginResponse1(String token) {
//		this.jwt_token = token;
//	}
}
