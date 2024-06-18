package com.bookstore.orderservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
	private long customerId;
	private List<BookOrder> orderedbooks;

}
