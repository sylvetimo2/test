package com.bookstore.orderservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderResult extends OrderResponse {
	private List<BookOrder> orderedbooks;
//	private List<OrderItem> orderedbooks;

}
