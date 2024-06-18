package com.bookstore.orderservice.dto;

import com.bookstore.orderservice.constant.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class OrderResponse {
	private Status Status;
	private Long orderId;
	private Long customerId;

	@AllArgsConstructor
	@Data
	public class Status {
		OrderStatus status;
		String description;
	}
}
