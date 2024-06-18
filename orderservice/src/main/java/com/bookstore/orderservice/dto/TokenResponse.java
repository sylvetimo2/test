package com.bookstore.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@JsonFormat
@Data
public class TokenResponse {
	private String access_token;
	private String scope;
	private String token_type;
	private String expires_in;

}
