package com.bookstore.orderservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests -> {
			try {
				authorizeRequests.antMatchers("/actuator/**", "/api-docs/**").permitAll().and()
						.csrf(csrf -> csrf.disable()).authorizeRequests(
								requests -> requests.anyRequest().access("hasAnyAuthority('SCOPE_VIEW_BOOK')"));
			} catch (final Exception e) {
				log.error("SecurityConfigException {}", e.getMessage());
			}
		}).oauth2ResourceServer(server -> server.jwt());
	}

}
