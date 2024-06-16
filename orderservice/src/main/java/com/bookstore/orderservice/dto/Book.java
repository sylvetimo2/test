package com.bookstore.orderservice.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@JsonFormat
public class Book {
	private String bookId;
	private String authorId;
	private String bookTitle;
	private String authorName;
	private String serialNumber;
	private BigDecimal price;
}
