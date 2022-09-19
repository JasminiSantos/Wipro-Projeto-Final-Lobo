package com.squadlobo.api.service;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.squadlobo.api.dto.ClienteDTO;
import com.squadlobo.api.model.Cliente;
import com.squadlobo.api.repository.ClienteRepository;
import com.squadlobo.api.service.exceptions.ObjetoNaoEncontradoException;

@SpringBootTest
public class ClienteServiceTests {

	private static final LocalDate DATA_NASCIMENTO = LocalDate.parse("2001-04-23");

	private static final double RENDA_CC = 1000.0;

	private static final String TELEFONE = "48982874478";

	private static final String NOME = "Bryan Isaac";

	private static final String CPF = "90584196229";

	private static final int INDEX = 0;

	private static final String CPF_NAO_ENCONTRADO = "CPF não encontado!";

	@InjectMocks
	private ClienteService clienteService;

	@Mock
	private ClienteRepository clienteRepository;

	private Cliente cliente;
	private ClienteDTO clienteDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void quandoListarClientesRetorneUmaListaDeClientes() {
		when(clienteRepository.findAll()).thenReturn(List.of(cliente));

		List<Cliente> response = clienteService.listarClientes();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Cliente.class, response.get(INDEX).getClass());

		assertEquals(CPF, response.get(INDEX).getCpf());
		assertEquals(NOME, response.get(INDEX).getNome());
		assertEquals(DATA_NASCIMENTO, response.get(INDEX).getDataNascimento());
		assertEquals(TELEFONE, response.get(INDEX).getTelefone());
		assertEquals(RENDA_CC, response.get(INDEX).getRendaMensal());
	}

	@Test
	void quandoBuscarCpfRetorneSucesso() {
		when(clienteRepository.findById(anyString())).thenReturn(Optional.of(cliente));

		Cliente response = clienteService.buscarCpf(CPF);

		assertNotNull(response);
		assertEquals(Cliente.class, response.getClass());
		assertEquals(CPF, response.getCpf());
		assertEquals(NOME, response.getNome());
		assertEquals(DATA_NASCIMENTO, response.getDataNascimento());
		assertEquals(TELEFONE, response.getTelefone());
		assertEquals(RENDA_CC, response.getRendaMensal());
	}

	@Test
	void quandoBuscarCpfRetorneUmObjetoNaoEncontradoException() {
		when(clienteRepository.findById(anyString())).thenThrow(new ObjetoNaoEncontradoException(CPF_NAO_ENCONTRADO));

		ObjetoNaoEncontradoException objetoNaoEncontradoException = assertThrows(ObjetoNaoEncontradoException.class,
				() -> clienteService.buscarCpf(CPF));
		assertEquals(CPF_NAO_ENCONTRADO, objetoNaoEncontradoException.getLocalizedMessage());
	}

	@Test
	void quandoAtualizarClienteRetorneSucesso() {
		when(clienteRepository.findById(anyString())).thenReturn(Optional.of(cliente));
		when(clienteRepository.save(any())).thenReturn(cliente);

		Cliente response = clienteService.atualizarCliente(clienteDTO);

		assertNotNull(response);
		assertEquals(Cliente.class, response.getClass());
		assertEquals(CPF, response.getCpf());
		assertEquals(NOME, response.getNome());
		assertEquals(DATA_NASCIMENTO, response.getDataNascimento());
		assertEquals(TELEFONE, response.getTelefone());
		assertEquals(RENDA_CC, response.getRendaMensal());
	}

	@Test
	void quandoAtualizarClienteRetorneUmObjetoNaoEncontradoException() {
		when(clienteRepository.save(any())).thenReturn(new ObjetoNaoEncontradoException(CPF_NAO_ENCONTRADO));

		ObjetoNaoEncontradoException objetoNaoEncontradoException = assertThrows(ObjetoNaoEncontradoException.class,
				() -> clienteService.atualizarCliente(clienteDTO));
		assertEquals(CPF_NAO_ENCONTRADO, objetoNaoEncontradoException.getLocalizedMessage());
	}

	private void startUser() {
		cliente = new Cliente(NOME, CPF, DATA_NASCIMENTO, TELEFONE, RENDA_CC);
		clienteDTO = new ClienteDTO(cliente);
	}
}
