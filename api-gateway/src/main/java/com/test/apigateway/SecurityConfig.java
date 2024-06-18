package com.test.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

//
//@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {
	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http.authorizeExchange(exchange -> exchange.pathMatchers("/actuator/**").permitAll())
				.csrf(csrf -> csrf.disable().authorizeExchange(exchange -> exchange.anyExchange().authenticated()))
				.oauth2ResourceServer(server -> server.jwt());
		// .oauth2Login(); // to redirect to oauth2 login page.

		return http.build();
	}
}
