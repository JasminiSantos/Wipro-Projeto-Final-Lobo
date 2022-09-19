package com.squadlobo.api.dto;

import javax.validation.Valid;

public class ContaRequestDTO {

	@Valid
	private ClienteDTO cliente;

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
}