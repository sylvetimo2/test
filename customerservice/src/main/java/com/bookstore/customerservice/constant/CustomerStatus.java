package com.bookstore.customerservice.constant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat
public enum CustomerStatus {
	SUCCESS("SUCCESS"), DUPLICATE("DUPLICATE CUSTOMER"), ERROR("ERROR"), NOT_FOUND("NOT_FOUND");

	private String label;

	CustomerStatus(String label) {
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
