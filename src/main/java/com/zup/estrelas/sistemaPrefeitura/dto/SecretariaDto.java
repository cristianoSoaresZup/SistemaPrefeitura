package com.zup.estrelas.sistemaPrefeitura.dto;

import java.util.List;

import com.zup.estrelas.sistemaPrefeitura.entity.FuncionarioEntity;
import com.zup.estrelas.sistemaPrefeitura.entity.ProjetoEntity;
import com.zup.estrelas.sistemaPrefeitura.entity.SecretariaEntity;

public class SecretariaDto {

	private String area;
	private Double orcamentoProjetos;
	private Double orcamentoFolha;
	private String telefone;
	private String endereco;
	private String site;
	private String email;
	private List<FuncionarioEntity> funcionarios;
	private List<ProjetoEntity> projetos;
	
	public SecretariaEntity transformaPraObjeto() {
		return new SecretariaEntity(area, orcamentoProjetos, orcamentoFolha, telefone, endereco, site, email, funcionarios, projetos);
	}

	public SecretariaDto(String area, Double orcamentoProjetos, Double orcamentoFolha,
			String telefone, String endereco, String site, String email) {
		super();
		this.area = area;
		this.orcamentoProjetos = orcamentoProjetos;
		this.orcamentoFolha = orcamentoFolha;
		this.telefone = telefone;
		this.endereco = endereco;
		this.site = site;
		this.email = email;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Double getOrcamentoProjetos() {
		return orcamentoProjetos;
	}

	public void setOrcamentoProjetos(Double orcamentoProjetos) {
		this.orcamentoProjetos = orcamentoProjetos;
	}

	public Double getOrcamentoFolha() {
		return orcamentoFolha;
	}

	public void setOrcamentoFolha(Double orcamentoFolha) {
		this.orcamentoFolha = orcamentoFolha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<FuncionarioEntity> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioEntity> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<ProjetoEntity> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<ProjetoEntity> projetos) {
		this.projetos = projetos;
	}

}
