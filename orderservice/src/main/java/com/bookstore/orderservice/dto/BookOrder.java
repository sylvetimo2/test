package com.bookstore.orderservice.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@JsonFormat
public class BookOrder {
	private Long bookId;
//	private String bookTitle;
//	private String authorName;
//	private String serialNumber;
	private BigDecimal price;
}
