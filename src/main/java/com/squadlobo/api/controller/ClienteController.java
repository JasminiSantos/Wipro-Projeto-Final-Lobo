package com.squadlobo.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squadlobo.api.dto.ClienteDTO;
import com.squadlobo.api.model.Cliente;
import com.squadlobo.api.service.ClienteService;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> listarClientes() {
		List<Cliente> list = clienteService.listarClientes();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<Cliente> buscarCpf(@PathVariable @Valid String cpf) {
		Cliente obj = clienteService.buscarCpf(cpf);
		return ResponseEntity.ok().body(obj);
	}

	@PutMapping("/{cpf}")
	public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable @Valid String cpf,
			@Valid @RequestBody ClienteDTO objDTO) {
		objDTO.setCpf(cpf);
		ClienteDTO newObj = new ClienteDTO(clienteService.atualizarCliente(objDTO));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(newObj);
	}
}