package com.squadlobo.api.controller.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.squadlobo.api.service.exceptions.ObjetoNaoEncontradoException;

@SpringBootTest
public class ControllerExceptionTests {

	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado!";

	@InjectMocks
	private ControllerException controllerException;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void quandoObjetoNaoEncontradoRetorneResponseEntity() {
		ResponseEntity<StandardError> response = controllerException.objetoNaoEncontradoException(
				new ObjetoNaoEncontradoException(OBJETO_NAO_ENCONTRADO), new MockHttpServletRequest());

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals(404, response.getBody().getStatus());
		assertEquals("Not found", response.getBody().getError());
		assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getMessage());
		assertNotEquals("/cliente/2", response.getBody().getPath());
	}

}
