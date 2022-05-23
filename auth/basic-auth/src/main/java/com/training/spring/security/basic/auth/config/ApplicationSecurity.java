package com.training.spring.security.basic.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {//
		// in memory authentication
		auth.inMemoryAuthentication()
		.withUser("admin").password(passwordEncoder.encode("admin12345")).roles("ADMIN")
		.and()
		.withUser("user").password(passwordEncoder.encode("user12345")).roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// authorization should start from most restrictive url to least restrictive url
		http.authorizeRequests()
		.antMatchers("/api/admin/**").hasRole("ADMIN")
		.antMatchers("/auth/**").hasAnyRole("ADMIN", "USER")
		.antMatchers("/api/test/**").permitAll()
		.and()
		.httpBasic();// enabling http basic authentication

	}

}
