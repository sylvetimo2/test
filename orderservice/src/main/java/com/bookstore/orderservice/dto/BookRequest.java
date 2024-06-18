package com.bookstore.orderservice.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@JsonFormat
public class BookRequest {
	@NotBlank
	private String bookTitle;
	@NotBlank
	private String authorName;

	@NotBlank
	private String serialNumber;
	private Long bookId;
}
