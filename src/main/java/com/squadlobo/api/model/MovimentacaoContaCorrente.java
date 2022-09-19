package com.squadlobo.api.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SQLDelete(sql = "UPDATE movimentacao_conta_corrente SET ativo = false WHERE id = ?")
@Where(clause = "ativo = true")
@Entity
public class MovimentacaoContaCorrente extends Movimentacao {

	@ManyToOne
	@JoinColumn(name = "numero_conta")
	private ContaCorrente contaCorrente;

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	}
}
