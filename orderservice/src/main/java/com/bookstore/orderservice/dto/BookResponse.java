package com.bookstore.orderservice.dto;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.orderservice.constant.BookStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonFormat
public class BookResponse {
	private Status status;
	private List<BookRequest> bookRequest;

	@AllArgsConstructor
	@Data
	public class Status {
		BookStatus Status;
		String description;

		public Status() {

		}
	}

	public List<BookRequest> getBookRequest() {
		if (bookRequest == null) {
			bookRequest = new ArrayList<>();
		}
		return bookRequest;
	}
}
