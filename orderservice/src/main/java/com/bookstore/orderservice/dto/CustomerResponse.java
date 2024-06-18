package com.bookstore.orderservice.dto;

import com.bookstore.orderservice.constant.CustomerStatus;
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
	@JsonFormat
	public class Status {
		CustomerStatus status;
		String description;

		public Status() {

		}
	}

}
