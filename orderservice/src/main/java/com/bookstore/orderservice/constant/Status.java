package com.bookstore.orderservice.constant;

public enum Status {
	ACTIVE("ACTIVE"), FAILED("FAILED"), DISABLED("DISABLED");

	private String label;

	private Status(String label) {
		this.label = label;

	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
