package com.squadlobo.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.squadlobo.api.dto.ClienteDTO;
import com.squadlobo.api.dto.ContaRequestDTO;
import com.squadlobo.api.dto.MovimentacaoDTO;
import com.squadlobo.api.mapper.ContaMapper;
import com.squadlobo.api.model.Conta;
import com.squadlobo.api.model.ContaCorrente;
import com.squadlobo.api.model.ContaEspecial;
import com.squadlobo.api.model.TipoConta;
import com.squadlobo.api.repository.ClienteRepository;
import com.squadlobo.api.repository.ContaCorrenteRepository;
import com.squadlobo.api.repository.ContaEspecialRepository;
import com.squadlobo.api.repository.ContaRepository;
import com.squadlobo.api.service.exceptions.ObjetoNaoEncontradoException;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

	@InjectMocks
	private ContaService contaService;

	@Mock
	private ContaRepository contaRepository;

	@Mock
	private ClienteRepository clienteRepository;

	@Mock
	private ContaEspecialRepository contaEspecialRepository;

	@Mock
	private ContaCorrenteRepository contaCorrenteRepository;

	@Mock
	private MovimentacaoService movimentacaoService;

	@Mock
	private ContaMapper mapper;

	@BeforeEach
	void setUp() {
		ReflectionTestUtils.setField(contaService, "tetoContaEspecial", 2000D);
	}

	@Test
	void criarContaCorrente() {
		when(clienteRepository.countByCpf(anyString())).thenReturn(0L);
		ContaRequestDTO c = criarContaRequesteDTO(1500D);
		when(mapper.toContaCorrente(c)).thenReturn(new ContaCorrente());
		Conta contaCriada = contaService.criarConta(c);
		assertEquals(contaCriada.getTipoConta(), TipoConta.CORRENTE);
		assertEquals(contaCriada.getSaldo(), 0D);
	}

	@Test
	void criarContaEspecial() {
		when(clienteRepository.countByCpf(anyString())).thenReturn(0L);
		ContaRequestDTO c = criarContaRequesteDTO(2000D);
		when(mapper.toContaEspecial(c)).thenReturn(new ContaEspecial());
		ContaEspecial contaCriada = (ContaEspecial) contaService.criarConta(c);
		assertEquals(contaCriada.getTipoConta(), TipoConta.ESPECIAL);
		assertEquals(contaCriada.getLimiteUtilizado(), 0D);
		assertEquals(contaCriada.getLimiteContaEspecial(), 200D);
	}

	@Test
	void deveSubirUmaExeceptionQuandoContaNaoForEncontrada() {
		when(contaCorrenteRepository.findById(anyString())).thenReturn(Optional.empty());
		when(contaEspecialRepository.findById(anyString())).thenReturn(Optional.empty());
		ObjetoNaoEncontradoException objetoNaoEncontradoException = assertThrows(ObjetoNaoEncontradoException.class,
				() -> contaService.buscarConta("85748"));
		String msg = objetoNaoEncontradoException.getLocalizedMessage();
		assertEquals("Conta não encontrada!", msg);
	}

	@Test
	void deveSubirUmaExeceptionQuandoCpfEstiverSendoUtilizado() {
		ContaRequestDTO conta = criarContaRequesteDTO(2000D);
		when(clienteRepository.countByCpf("05384973609")).thenReturn(1l);
		ObjetoNaoEncontradoException objetoNaoEncontradoException = assertThrows(ObjetoNaoEncontradoException.class,
				() -> contaService.criarConta(conta));
		String msg = objetoNaoEncontradoException.getLocalizedMessage();
		assertEquals("CPF já cadastrado!", msg);

	}

	@Test
	void deveGerarOutroNumeroContaQuandoNumeroContaJaEstiverCaddastrado() {
		ContaRequestDTO conta = criarContaRequesteDTO(1500D);
		when(mapper.toContaCorrente(conta)).thenReturn(new ContaCorrente());
		when(contaCorrenteRepository.existsById(anyString())).thenReturn(Boolean.TRUE, Boolean.FALSE);
		contaService.criarConta(conta);
	}

	@Test
	void contaCorrenteEncontrada() {
		ContaRequestDTO conta = criarContaRequesteDTO(1500D);
		when(mapper.toContaCorrente(conta)).thenReturn(new ContaCorrente());
		contaService.criarConta(conta);
		when(contaCorrenteRepository.findById(anyString())).thenReturn(Optional.of(new ContaCorrente()));
		contaService.buscarConta(anyString());
	}

	@Test
	void contaEspecialEncontrada() {
		ContaRequestDTO conta = criarContaRequesteDTO(2000D);
		when(mapper.toContaEspecial(conta)).thenReturn(new ContaEspecial());
		contaService.criarConta(conta);
		when(contaEspecialRepository.findById(anyString())).thenReturn(Optional.of(new ContaEspecial()));
		contaService.buscarConta(anyString());
	}

	@Test
	void listarContaCorrente() {
		when(contaCorrenteRepository.findAll()).thenReturn(List.of(new ContaCorrente()));
		contaService.listarContacorrente();
		verify(contaCorrenteRepository, times(1)).findAll();
	}

	@Test
	void listarContaEspecial() {
		when(contaEspecialRepository.findAll()).thenReturn(List.of(new ContaEspecial()));
		contaService.listarContaEspecial();
		verify(contaEspecialRepository, times(1)).findAll();
	}

	@Test
	void deletarConta() {
		ContaRequestDTO conta = criarContaRequesteDTO(1500D);
		when(mapper.toContaCorrente(conta)).thenReturn(new ContaCorrente());
		contaService.criarConta(conta);
		when(contaCorrenteRepository.findById(anyString())).thenReturn(Optional.of(new ContaCorrente()));
		contaService.deletarConta(anyString());
		verify(contaRepository, times(1)).delete(any());

	}

	@Test
	void depositar() {
		ContaCorrente contaCorrente = new ContaCorrente();
		contaCorrente.setAtivo(Boolean.TRUE);
		ReflectionTestUtils.setField(contaCorrente, "saldo", 0D);
		when(contaCorrenteRepository.findById(anyString())).thenReturn(Optional.of(contaCorrente));
		MovimentacaoDTO movimentacaoDTO = new MovimentacaoDTO();
		movimentacaoDTO.setValor(200D);
		contaService.depositar(anyString(), movimentacaoDTO);
		verify(movimentacaoService, times(1)).criarMovimentacao(any(), any(), anyDouble());
		verify(contaRepository, times(1)).save(any());
	}

	@Test
	void sacarContaCorrente() {
		ContaCorrente contaCorrente = new ContaCorrente();
		contaCorrente.setAtivo(Boolean.TRUE);
		ReflectionTestUtils.setField(contaCorrente, "saldo", 400D);
		when(contaCorrenteRepository.findById(anyString())).thenReturn(Optional.of(contaCorrente));
		MovimentacaoDTO movimentacaoDTO = new MovimentacaoDTO();
		movimentacaoDTO.setValor(200D);
		contaService.sacar(anyString(), movimentacaoDTO);
		verify(movimentacaoService, times(1)).criarMovimentacao(any(), any(), anyDouble());
		verify(contaRepository, times(1)).save(any());
	}

	@Test
	void sacarContaEspecial() {
		ContaEspecial contaEspecial = new ContaEspecial();
		contaEspecial.setAtivo(Boolean.TRUE);
		contaEspecial.setLimiteContaEspecial(1000D);
		contaEspecial.setLimiteUtilizado(0D);
		ReflectionTestUtils.setField(contaEspecial, "saldo", 0D);
		when(contaEspecialRepository.findById(anyString())).thenReturn(Optional.of(contaEspecial));
		MovimentacaoDTO movimentacaoDTO = new MovimentacaoDTO();
		movimentacaoDTO.setValor(200D);
		contaService.sacar(anyString(), movimentacaoDTO);
		verify(movimentacaoService, times(1)).criarMovimentacao(any(), any(), anyDouble());
		verify(contaRepository, times(1)).save(any());
	}

	private ContaRequestDTO criarContaRequesteDTO(Double renda) {
		ContaRequestDTO conta = new ContaRequestDTO();
		conta.setCliente(new ClienteDTO());
		conta.getCliente().setCpf("05384973609");
		conta.getCliente().setNome("Teste");
		conta.getCliente().setTelefone("34565789012");
		conta.getCliente().setDataNascimento(LocalDate.of(1990, 2, 05));
		conta.getCliente().setRendaMensal(renda);
		return conta;
	}

}