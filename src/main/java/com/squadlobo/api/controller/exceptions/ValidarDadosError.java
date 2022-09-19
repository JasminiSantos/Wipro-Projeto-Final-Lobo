package com.squadlobo.api.controller.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ValidarDadosError extends StandardError {

	private static final long serialVersionUID = 1L;

	private Map<String, String> errors = new HashMap<>();

	public ValidarDadosError() {
		super();
	}

	public ValidarDadosError(LocalDateTime timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void addError(String field, String message) {
		this.errors.put(field, message);

	}
}
