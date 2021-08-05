package com.hung.security.configuration;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.hibernate.mapping.Collection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.hung.security.filter.JWTTokenGeneratorFilter;
import com.hung.security.filter.JWTTokenValidatorFilter;
import com.hung.security.filter.RequestValidationBeforeFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

	/**
	 * Quản lý theo URL
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * cấu hình cors cho phép domain A (Client) gọi qua domain B (Backend) để lấy dữ liệu
		 */
		http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.cors().configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setExposedHeaders(Arrays.asList("Authorization"));
				config.setMaxAge(3600L);
				return config;
			}
			
		})
		/*
		 * Cấp cookie để phía client khi gọi lên server thì cầm cái cookie đó gửi lên
		 * biến DEFAULT_CSRF_COOKIE_NAME trong CookieCsrfTokenRepository
		 * cái này gọi là CFRF
		 */
		.and().csrf().disable()
//		.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		/*
		 * phân quyền truy cập URL
		 */
		.authorizeRequests()
				/**
				 * cho phép toàn bộ các url được truy cập
				 */
				// .anyRequest().permitAll()
				/**
				 * authenticated() ủy quyền cho những url có link như difine
				 */
				.antMatchers("/myBalance").hasRole("ADMIN")
				.antMatchers("/myLoans").hasRole("USER")
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
