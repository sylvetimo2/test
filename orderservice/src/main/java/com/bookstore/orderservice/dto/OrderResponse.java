package com.bookstore.orderservice.dto;

import com.bookstore.orderservice.constant.Status;

import lombok.Data;

@Data
public class OrderResponse {
	private long orderId;
	private long customerId;
	private Status Status;

}
