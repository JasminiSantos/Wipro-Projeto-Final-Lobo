package com.squadlobo.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.squadlobo.api.model.exceptions.DepositoInvalidoException;
import com.squadlobo.api.model.exceptions.SaldoInsuficienteException;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@SQLDelete(sql = "UPDATE conta_corrente SET ativo = false WHERE numero_conta = ?")
@Where(clause = "ativo = true")
@Entity
public class ContaCorrente extends Conta {

	@JsonIgnore
	@OneToMany(mappedBy = "contaCorrente", cascade = CascadeType.ALL)
	private List<MovimentacaoContaCorrente> movimentacoes;

	@Override
	public void sacar(Double valor) throws SaldoInsuficienteException {
		if (valor > 0 && valor <= getSaldo()) {
			Double novoSaldo = getSaldo() - valor;
			setSaldo(novoSaldo);
		} else {
			throw new SaldoInsuficienteException("Operação inválida!");
		}
	}

	@Override
	public void depositar(Double valor) throws DepositoInvalidoException {
		if (valor > 0) {
			setSaldo(getSaldo() + valor);
		} else {
			throw new DepositoInvalidoException("Operação inválida!");
		}
	}

	public List<MovimentacaoContaCorrente> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<MovimentacaoContaCorrente> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
}
