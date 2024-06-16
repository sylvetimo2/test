package com.bookstore.bookservice.dto;

import com.bookstore.bookservice.constants.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@JsonFormat
public class BookResponse {
	private String bookId;
	private String authorId;
	private String bookTitle;
	private String authorName;
	private String serialNumber;
	private Status status;
	private String description;
}
