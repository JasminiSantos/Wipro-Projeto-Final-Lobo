package com.squadlobo.api.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SQLDelete(sql = "UPDATE movimentacao_conta_especial SET ativo = false WHERE id = ?")
@Where(clause = "ativo = true")
@Entity
public class MovimentacaoContaEspecial extends Movimentacao {

	@ManyToOne
	@JoinColumn(name = "numero_conta")
	private ContaEspecial contaEspecial;

	public ContaEspecial getContaEspecial() {
		return contaEspecial;
	}

	public void setContaEspecial(ContaEspecial contaEspecial) {
		this.contaEspecial = contaEspecial;
	}
}
