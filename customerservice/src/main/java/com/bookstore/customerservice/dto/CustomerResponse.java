package com.bookstore.customerservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@JsonFormat
public class CustomerResponse {
	private String customerId;
	private String customerName;
	private String idNumber;

}
