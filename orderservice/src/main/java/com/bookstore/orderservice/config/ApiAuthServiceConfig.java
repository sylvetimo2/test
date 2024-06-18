package com.bookstore.orderservice.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;

import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Encoder;
import feign.form.FormEncoder;

public class ApiAuthServiceConfig {

	@Value("${consumer.key}")
	private String username;
	@Value("${consumer.secret}")
	private String password;
	@Autowired
	private ObjectFactory<HttpMessageConverters> messageConverters;

	@Bean
	Encoder feignFormEncoder() {
		return new FormEncoder(new SpringEncoder(this.messageConverters));
	}

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor(username, password);
	}

}
