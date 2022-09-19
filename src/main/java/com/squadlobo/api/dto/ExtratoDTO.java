package com.squadlobo.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.squadlobo.api.model.Movimentacao;
import com.squadlobo.api.model.TipoMovimentacao;

public class ExtratoDTO {
	
	private TipoMovimentacao tipoMovimentacao;
	private Double valor;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHoraMovimentacao;
	
	public ExtratoDTO() {
		super();
	}
	
	public ExtratoDTO(Movimentacao obj) {
		super();
		this.tipoMovimentacao = obj.getTipoMovimentacao();
		this.valor = obj.getValor();
		this.dataHoraMovimentacao = obj.getDataHoraMovimentacao().toLocalDateTime();
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataHoraMovimentacao() {
		return dataHoraMovimentacao;
	}

	public void setDataHoraMovimentacao(LocalDateTime dataHoraMovimentacao) {
		this.dataHoraMovimentacao = dataHoraMovimentacao;
	}
	
	

}
