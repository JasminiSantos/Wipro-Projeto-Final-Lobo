package com.squadlobo.api.model.exceptions;

public class SaldoInsuficienteException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public SaldoInsuficienteException(String message, Throwable cause) {
		super(message, cause);
	}

	public SaldoInsuficienteException(String message) {
		super(message);
	}
}
