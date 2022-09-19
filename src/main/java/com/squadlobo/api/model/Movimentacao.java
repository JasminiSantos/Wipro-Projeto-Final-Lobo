package com.squadlobo.api.model;

import java.time.ZonedDateTime;

import javax.persistence.*;

@MappedSuperclass
public abstract class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;

	private Boolean ativo;

	private Double valor;

	@Column(nullable = false)
	private ZonedDateTime dataHoraMovimentacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ZonedDateTime getDataHoraMovimentacao() {
		return dataHoraMovimentacao;
	}

	public void setDataHoraMovimentacao(ZonedDateTime dataHoraMovimentacao) {
		this.dataHoraMovimentacao = dataHoraMovimentacao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@PrePersist
	public void prePersist() {
		this.setAtivo(Boolean.TRUE);
	}

}
