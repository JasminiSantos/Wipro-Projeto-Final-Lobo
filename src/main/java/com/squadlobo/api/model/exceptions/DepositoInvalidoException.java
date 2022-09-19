package com.squadlobo.api.model.exceptions;

public class DepositoInvalidoException extends RuntimeException {	
	private static final long serialVersionUID = 1L;
	
	public DepositoInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DepositoInvalidoException(String message) {
		super(message);
	}
}
