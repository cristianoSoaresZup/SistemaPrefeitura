package com.zup.estrelas.sistemaPrefeitura.dto;

import java.time.LocalDate;

import com.zup.estrelas.sistemaPrefeitura.entity.ProjetoEntity;

public class ProjetoDto {
	
	private Long idSecretaria;
	private String nome;
	private String descricao;
	private Double custo;
	private LocalDate dataInicio;
	private LocalDate dataEntrega;
	private Boolean concluido;
	
	public ProjetoDto(Long idSecretaria, String nome, String descricao, Double custo) {
	
		this.idSecretaria = idSecretaria;
		this.nome = nome;
		this.descricao = descricao;
		this.custo = custo;
	}
	
	public ProjetoEntity transformaParaObjeto() {
		return new ProjetoEntity(nome, descricao, custo, dataInicio, dataEntrega, concluido, idSecretaria);
	}

	public Long getIdSecretaria() {
		return idSecretaria;
	}



	public void setIdSecretaria(Long idSecretaria) {
		this.idSecretaria = idSecretaria;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Boolean getConcluido() {
		return concluido;
	}

	public void setConcluido(Boolean concluido) {
		this.concluido = concluido;
	}
	

}
