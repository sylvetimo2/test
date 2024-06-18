package com.bookstore.orderservice.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat
public enum OrderStatus {
	SUCCESS("SUCCESS"), FAILED("FAILED"), CANCELLED("CANCELLED"), PAID("PAID"), NOT_FOUND("NOT_FOUND");

	private String label;

	private OrderStatus(String label) {
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
