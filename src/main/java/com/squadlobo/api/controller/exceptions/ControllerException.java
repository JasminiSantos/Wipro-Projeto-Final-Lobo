package com.squadlobo.api.controller.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.squadlobo.api.model.exceptions.DepositoInvalidoException;
import com.squadlobo.api.model.exceptions.SaldoInsuficienteException;
import com.squadlobo.api.service.exceptions.ObjetoNaoEncontradoException;

@ControllerAdvice
public class ControllerException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> dadosInvalidosException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		ValidarDadosError error = new ValidarDadosError();
		error.setTimestamp((LocalDateTime.now()));
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("Bad Request");
		error.setMessage("Dados inválidos!");
		error.setPath(request.getRequestURI());
		ex.getBindingResult().getAllErrors()
				.forEach(x -> error.addError(((FieldError) x).getField(), x.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<StandardError> objetoNaoEncontradoException(ObjetoNaoEncontradoException ex,
			HttpServletRequest request) {

		StandardError error = new StandardError();
		error.setTimestamp((LocalDateTime.now()));
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Not found");
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(SaldoInsuficienteException.class)
	public ResponseEntity<StandardError> saldoInsuficienteException(SaldoInsuficienteException ex,
			HttpServletRequest request) {

		StandardError error = new StandardError();
		error.setTimestamp((LocalDateTime.now()));
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("Bad Request");
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(DepositoInvalidoException.class)
	public ResponseEntity<StandardError> depositoInvalidoException(DepositoInvalidoException ex,
			HttpServletRequest request) {

		StandardError error = new StandardError();
		error.setTimestamp((LocalDateTime.now()));
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("Bad Request");
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
