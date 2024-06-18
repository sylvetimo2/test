package com.bookstore.orderservice.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.bookstore.orderservice.service.TokenService;

import feign.RequestInterceptor;

public class TokenApiConfig {
	@Autowired
	private TokenService tokenService;

	/*
	 * @Bean Logger.Level feignLoggerLevel() { return Logger.Level.FULL; }
	 */

	@Bean
	public RequestInterceptor requestInterceptor() {

		Map<String, String> param = new HashMap<>();
		param.put("grant_type", "client_credentials");
		return requestTemplate -> {
			requestTemplate.header("Authorization", "Bearer " + tokenService.getAccessToken(param).getAccess_token());

		};
	}
}
