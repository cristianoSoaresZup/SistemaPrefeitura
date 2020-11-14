package com.zup.estrelas.sistemaPrefeitura.dto;

import java.time.LocalDate;

import com.zup.estrelas.sistemaPrefeitura.entity.FuncionarioEntity;
import com.zup.estrelas.sistemaPrefeitura.entity.SecretariaEntity;

public class FuncionarioDto {
	private Long idFuncionario;
	private String nome;
	private String cpf;
	private Double salario;
	private String funcao;
	private Boolean concursado;
	private LocalDate dataAdmissao;
	private Long idSecretaria;
	private SecretariaEntity secretaria;

	public FuncionarioDto(String nome, String cpf, Double salario, String funcao, Boolean concursado,
			LocalDate dataAdmissao, Long idSecretaria) {
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.funcao = funcao;
		this.concursado = concursado;
		this.dataAdmissao = dataAdmissao;
		this.idSecretaria = idSecretaria;
	}

	public FuncionarioEntity transformaParaObjeto() {

		return new FuncionarioEntity(nome, cpf, salario, funcao, concursado, dataAdmissao, idSecretaria);

	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public Boolean getConcursado() {
		return concursado;
	}

	public void setConcursado(Boolean concursado) {
		this.concursado = concursado;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Long getIdSecretaria() {
		return idSecretaria;
	}

	public void setIdSecretaria(Long idSecretaria) {
		this.idSecretaria = idSecretaria;
	}

	public SecretariaEntity getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(SecretariaEntity secretaria) {
		this.secretaria = secretaria;
	}

}
