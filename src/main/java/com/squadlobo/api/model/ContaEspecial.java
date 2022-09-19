package com.squadlobo.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.squadlobo.api.model.exceptions.DepositoInvalidoException;
import com.squadlobo.api.model.exceptions.SaldoInsuficienteException;

@SQLDelete(sql = "UPDATE conta_especial SET ativo = false WHERE numero_conta = ?")
@Where(clause = "ativo = true")
@Entity
public class ContaEspecial extends Conta {

	private Double limiteContaEspecial;
	private Double limiteUtilizado;

	@JsonIgnore
	@OneToMany(mappedBy = "contaEspecial", cascade = CascadeType.ALL)
	private List<MovimentacaoContaEspecial> movimentacoes;

	public Double getLimiteContaEspecial() {
		return limiteContaEspecial;
	}

	public void setLimiteContaEspecial(Double limiteContaEspecial) {
		this.limiteContaEspecial = limiteContaEspecial;
	}

	public Double getLimiteUtilizado() {
		return limiteUtilizado;
	}

	public void setLimiteUtilizado(Double limiteUtilizado) {
		this.limiteUtilizado = limiteUtilizado;
	}

	public List<MovimentacaoContaEspecial> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<MovimentacaoContaEspecial> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	@Override
	public void sacar(Double valor) throws SaldoInsuficienteException {
		if (valor > 0 && valor <= getSaldo()) {
			setSaldo(getSaldo() - valor);
		} else if (valor > 0 && valor <= (getSaldo() + (limiteContaEspecial - limiteUtilizado))) {
			limiteUtilizado += (valor - getSaldo());
			setSaldo(getSaldo() - limiteUtilizado);
		} else {
			throw new SaldoInsuficienteException("Saque inválido!");
		}
	}

	@Override
	public void depositar(Double valor) throws DepositoInvalidoException {
		if (valor > 0) {
			if (limiteUtilizado > 0) {
				if (valor <= limiteUtilizado) {
					limiteUtilizado -= valor;
					valor = 0d;
				} else {
					valor -= limiteUtilizado;
					limiteUtilizado = 0d;
				}
			}
			setSaldo(getSaldo() + valor);
		} else {
			throw new DepositoInvalidoException("Depósito inválido!");
		}
	}

}
