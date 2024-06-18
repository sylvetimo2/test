package com.bookstore.orderservice.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookstore.orderservice.config.ApiAuthServiceConfig;
import com.bookstore.orderservice.dto.TokenResponse;

import feign.Headers;

//@Headers("Content-Type: application/json")
@FeignClient(name = "TOKEN-SERVICE", url = "${auth.url}", configuration = ApiAuthServiceConfig.class)
public interface TokenService {
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@Headers("Content-Type: application/x-www-form-urlencoded")
	public TokenResponse getAccessToken(Map<String, ?> formParams);

}
