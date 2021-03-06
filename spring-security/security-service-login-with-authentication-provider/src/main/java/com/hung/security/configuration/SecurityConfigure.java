package com.hung.security.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

	/**
	 * Quản lý theo URL
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				/**
				 * cho phép toàn bộ các url được truy cập
				 */
				// .anyRequest().permitAll()
				/**
				 * authenticated() ủy quyền cho những url có link như difine
				 */
				.antMatchers("/user/*", "/card/*").authenticated()
				/**
				 * permitAll() cho phép quyền cho những url có link như difine
				 */
				.antMatchers("/contact/*", "/notice/*").permitAll()
				/**
				 * denyAll() phủ nhận quyền cho những url có link như difine
				 */
				.antMatchers("/rest/*").denyAll().and().formLogin().and().httpBasic();
	}
	
	/**
	 * Quản lý theo User
	 */
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password("123456").authorities("admin").and()
//		.withUser("user").password("123456").authorities("read").and().passwordEncoder(NoOpPasswordEncoder.getInstance());
//	}
	
	
	/**
	 * Config User bằng InMemoryConfiguration
	 */
	/**@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
		UserDetails userAdmin = User.withUsername("admin").password("123456").authorities("admin").build(); 
		UserDetails userUser = User.withUsername("user").password("123456").authorities("read").build();
		userDetailsService.createUser(userAdmin);
		userDetailsService.createUser(userUser);
		auth.userDetailsService(userDetailsService);
	}*/

//	@Bean
//	public UserDetailsService userDetailsService(DataSource dataSource) {
//		return new JdbcUserDetailsManager(dataSource);
//	}
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
