package com.bookstore.customerservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@JsonFormat
public class CustomerRequest {
	private String firstName;
	private String lastName;
	private String idNumber;
	private String otherName;
}
