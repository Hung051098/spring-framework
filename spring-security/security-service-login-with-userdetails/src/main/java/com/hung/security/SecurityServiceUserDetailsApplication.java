package com.hung.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScans({@ComponentScan("com.hung.security.rest"), @ComponentScan("com.hung.security.configuration")})
@EnableJpaAuditing
public class SecurityServiceUserDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceUserDetailsApplication.class, args);
	}

}
