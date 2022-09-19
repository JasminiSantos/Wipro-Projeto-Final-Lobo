package com.squadlobo.api.service;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squadlobo.api.model.Conta;
import com.squadlobo.api.model.ContaCorrente;
import com.squadlobo.api.model.ContaEspecial;
import com.squadlobo.api.model.Movimentacao;
import com.squadlobo.api.model.MovimentacaoContaCorrente;
import com.squadlobo.api.model.MovimentacaoContaEspecial;
import com.squadlobo.api.model.TipoMovimentacao;
import com.squadlobo.api.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	

	public void criarMovimentacao(Conta conta, TipoMovimentacao tipoMovimentacao, Double valor) {
		Movimentacao movimentacao = null;
		if (conta instanceof ContaCorrente) {
			MovimentacaoContaCorrente mcc = new MovimentacaoContaCorrente();
			mcc.setContaCorrente((ContaCorrente) conta);
			movimentacao = mcc;
		} else {
			MovimentacaoContaEspecial mce = new MovimentacaoContaEspecial();
			mce.setContaEspecial((ContaEspecial) conta);
			movimentacao = mce;
		}
		movimentacao.setTipoMovimentacao(tipoMovimentacao);
		movimentacao.setDataHoraMovimentacao(ZonedDateTime.now());
		movimentacao.setValor(valor);
		movimentacaoRepository.save(movimentacao);
	}	
}
