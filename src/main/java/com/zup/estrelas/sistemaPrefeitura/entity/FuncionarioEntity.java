package com.zup.estrelas.sistemaPrefeitura.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "funcionario")
public class FuncionarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFuncionario;
	private String nome;
	private String cpf;
	private Double salario;
	private String funcao;
	private Boolean concursado;
	private LocalDate dataAdmissao;

	@Transient
	private Long idSecretaria;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "idSecretaria", nullable = false)
	private SecretariaEntity secretaria;

	public FuncionarioEntity(String nome, String cpf, Double salario, String funcao, Boolean concursado,
			LocalDate dataAdmissao, Long idSecretaria) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.funcao = funcao;
		this.concursado = concursado;
		this.dataAdmissao = dataAdmissao;
		this.idSecretaria = idSecretaria;
	}

	public FuncionarioEntity() {

	}

	public Long getIdSecretraria() {
		return idSecretaria;
	}

	public void setIdSecretraria(Long idSecretraria) {
		this.idSecretaria = idSecretraria;
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

	public SecretariaEntity getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(SecretariaEntity secretaria) {
		this.secretaria = secretaria;
	}

}
