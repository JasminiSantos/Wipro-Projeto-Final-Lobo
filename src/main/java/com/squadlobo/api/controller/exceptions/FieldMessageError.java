package com.squadlobo.api.controller.exceptions;

import java.io.Serializable;

public class FieldMessageError implements Serializable {

	private static final long serialVersionUID = 1L;

	private String field;
	private String message;

	public FieldMessageError() {
		super();
	}

	public FieldMessageError(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String fieldName) {
		this.field = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
