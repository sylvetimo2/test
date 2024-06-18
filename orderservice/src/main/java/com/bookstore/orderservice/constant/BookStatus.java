package com.bookstore.orderservice.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat
public enum BookStatus {
	SUCCESS("SUCCESS"), DUPLICATE("DUPLICATE BOOK"), ERROR("ERROR"), NOT_FOUND("NOT_FOUND");

	private String label;

	BookStatus(String label) {
		this.label = label;

	}

	@JsonValue
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
