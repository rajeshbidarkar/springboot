package com.api.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * This class is for Basic security config
 * 
 * @author
 *
 */
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// register test user with in memory authentication provider
		auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("ADMIN");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// enable basic authentication & disable anoymous access
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().anonymous().disable();
	}

}