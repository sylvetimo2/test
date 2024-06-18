package com.bookstore.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookstore.orderservice.config.TokenApiConfig;
import com.bookstore.orderservice.dto.CustomerResponse;

import feign.Headers;

@Headers("Content-Type: application/json")
@FeignClient(name = "CUSTOMER-SERVICE", configuration = TokenApiConfig.class)
public interface CustomerService {
	@GetMapping(produces = "application/json", value = "/customers/{id}")
	public CustomerResponse fetchCustomerById(@PathVariable("id") long id);
}
