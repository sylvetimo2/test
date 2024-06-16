package com.bookstore.bookservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonFormat
public class BookRequest {
	@NotBlank
	private String bookTitle;
	@NotBlank
	private String authorFirstName;
	@NotBlank
	private String authorLastName;
	@NotBlank
	private String serialNumber;
}
