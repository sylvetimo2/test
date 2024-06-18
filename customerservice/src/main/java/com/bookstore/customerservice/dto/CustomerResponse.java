package com.bookstore.customerservice.dto;

import com.bookstore.customerservice.constant.CustomerStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonFormat
public class CustomerResponse {
	private Long customerId;
	private String customerName;
	private String idNumber;
	private Status status;

	@AllArgsConstructor
	@Data
	public class Status {
		CustomerStatus status;
		String description;
	}

}
