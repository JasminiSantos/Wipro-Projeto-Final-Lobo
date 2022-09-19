package com.squadlobo.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.squadlobo.api.dto.ContaRequestDTO;
import com.squadlobo.api.dto.ContaResponseDTO;
import com.squadlobo.api.dto.ExtratoDTO;
import com.squadlobo.api.dto.MovimentacaoDTO;
import com.squadlobo.api.dto.TransferenciaDTO;
import com.squadlobo.api.mapper.ContaMapper;
import com.squadlobo.api.model.Conta;
import com.squadlobo.api.model.ContaCorrente;
import com.squadlobo.api.model.ContaEspecial;
import com.squadlobo.api.model.Movimentacao;
import com.squadlobo.api.service.ContaService;

@RestController
@RequestMapping("/conta")
@CrossOrigin("*")
public class ContaController {

	@Autowired
	private ContaService contaService;

	@Autowired
	private ContaMapper mapper;

	@GetMapping("/corrente")
	public ResponseEntity<List<ContaCorrente>> listarContacorrente() {
		List<ContaCorrente> list = contaService.listarContacorrente();
		return ResponseEntity.ok().body(list);
	}

	@DeleteMapping("/{numeroConta}")
	public ResponseEntity<Void> deletarConta(@PathVariable @Valid String numeroConta) {
		contaService.deletarConta(numeroConta);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/especial")
	public ResponseEntity<List<ContaEspecial>> listarContasEspecial() {
		List<ContaEspecial> list = contaService.listarContaEspecial();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{numeroConta}")
	public ResponseEntity<ContaResponseDTO> buscarConta(@PathVariable @Valid String numeroConta) {
		Conta obj = contaService.buscarConta(numeroConta);
		return ResponseEntity.ok(mapper.toContaResponseDto(obj));
	}

	@PostMapping
	public ResponseEntity<ContaResponseDTO> criarConta(@RequestBody @Valid ContaRequestDTO conta) {
		Conta contaNova = contaService.criarConta(conta);
		ContaResponseDTO response = mapper.toContaResponseDto(contaNova);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{numeroConta}")
				.buildAndExpand(contaNova.getNumeroConta()).toUri();
		return ResponseEntity.created(uri).body(response);
	}

	@PatchMapping("/deposito/{numeroConta}")
	public ResponseEntity<Void> depositar(@PathVariable @Valid String numeroConta,
			@RequestBody @Valid MovimentacaoDTO movimentacao) {
		contaService.depositar(numeroConta, movimentacao);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/saque/{numeroConta}")
	public ResponseEntity<Void> sacar(@PathVariable @Valid String numeroConta,
			@RequestBody @Valid MovimentacaoDTO movimentacao) {
		contaService.sacar(numeroConta, movimentacao);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/tranferencia")
	public ResponseEntity<Void> tranferir(@RequestBody @Valid TransferenciaDTO transferencia) {
		contaService.tranferir(transferencia);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/extrato/{numeroConta}")
	public ResponseEntity<List<?>> buscarExtrato(@PathVariable @Valid String numeroConta) {
		List<?> lista = contaService.buscarExtrato(numeroConta);
		List<ExtratoDTO> listaDto = lista.stream().map(obj -> new ExtratoDTO((Movimentacao) obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDto);
	}
}