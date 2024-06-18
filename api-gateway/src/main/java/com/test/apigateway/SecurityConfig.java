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
		http.authorizeExchange()
				.pathMatchers("/actuator/**", "/callback/**", "/airtel-c2b/**", "/mpesa-c2b/**", "/tkash-c2b/**",
						"/authorized/**")
				.permitAll().and().csrf().disable().authorizeExchange().anyExchange().authenticated().and()
				.oauth2ResourceServer().jwt();
		// .oauth2Login(); // to redirect to oauth2 login page.

		return http.build();
	}
}
