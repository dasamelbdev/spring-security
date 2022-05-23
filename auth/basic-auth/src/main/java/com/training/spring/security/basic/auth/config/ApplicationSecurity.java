package com.training.spring.security.basic.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {//
		// in memory authentication
		auth.inMemoryAuthentication().withUser("admin").password("admin12345").roles("ADMIN").and().withUser("user")
				.password("user12345").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// authorization should start from most restrictive url to  least restrictive url
		http
		.authorizeRequests()
		.antMatchers("/api/admin/**").hasRole("ADMIN")
		.antMatchers("/auth/**").hasAnyRole("ADMIN", "USER")
		.antMatchers("/api/test/**").permitAll()
		.and()
		.formLogin();//enabling form login

	}

}
