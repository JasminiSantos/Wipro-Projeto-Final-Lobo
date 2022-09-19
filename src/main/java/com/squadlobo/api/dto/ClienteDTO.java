package com.squadlobo.api.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.squadlobo.api.model.Cliente;

public class ClienteDTO {

	@Id
	@CPF(message = "CPF inválido!")
	@Size(min = 11, max = 11, message = "O CPF deve conter 11 digitos!")
	private String cpf;

	@Size(min = 2, max = 250, message = "O nome deve ter no mínimo 2 e no máximo 250 caracteres!")
	@NotBlank(message = "O nome não pode ser nulo ou vazio!")
	private String nome;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "A data não pode ser nula ou vazia!")
	@Column(nullable = false)
	private LocalDate dataNascimento;

	@Size(min = 10, max = 11, message = "O telefone deve ter no mínimo 10 e no máximo 11 caracteres!")
	@NotBlank(message = "O telefone não pode ser nulo ou vazio!")
	private String telefone;

	@Min(value = 0, message = "O valor deve ser maior ou igual a 0")
	@Column(nullable = false)
	private Double rendaMensal;

	
	public ClienteDTO() {
		super();
	}
	
	public ClienteDTO(Cliente obj) {
		super();
		this.cpf = obj.getCpf();
		this.nome = obj.getNome();
		this.dataNascimento = obj.getDataNascimento();
		this.telefone = obj.getTelefone();
		this.rendaMensal = obj.getRendaMensal();
	}	

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(Double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}
}