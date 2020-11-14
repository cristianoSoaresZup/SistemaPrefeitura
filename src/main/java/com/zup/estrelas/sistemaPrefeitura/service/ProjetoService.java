package com.zup.estrelas.sistemaPrefeitura.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.estrelas.sistemaPrefeitura.dto.MensagemDto;
import com.zup.estrelas.sistemaPrefeitura.dto.ProjetoDto;
import com.zup.estrelas.sistemaPrefeitura.entity.ProjetoEntity;
import com.zup.estrelas.sistemaPrefeitura.entity.SecretariaEntity;
import com.zup.estrelas.sistemaPrefeitura.repository.ProjetoRepository;
import com.zup.estrelas.sistemaPrefeitura.repository.SecretariaRepository;

@Service
public class ProjetoService {

	public static final String PROJETO_CADASTRADO_COM_SUCESSO = "Projeto cadastrado com sucesso";
	public static final String FUNCIONARIO_ALTERADO_COM_SUCESSO = "Funcionario alterado com sucesso";
	public static final String SECRETARIA_NAO_PODE_SER_ALTERADA_POR_ORCAMENTO = "Secretaria não pode ser alterada por problemas de orçamento";
	public static final String SECRETARIA_NAO_EXISTE = "O id da secretaria informado não existe";
	public static final String ORCAMENTO_DE_PROJETO_NAO_EH_SUFICIENTE = "O orçamento para projetos na secretaria não cobre os custosl";
	public static final String CAMPO_NULO_OU__EM_BRANCO = "Preencha os campos necessarios para cadastrar/alterar um novo projeto";
	public static final String PROJETO_NAO_ENCONTRADO = "Projeto não encontrado";

	@Autowired
	ProjetoRepository projetoRepository;
	SecretariaRepository secretariaRepository;

	public MensagemDto insereProjeto(ProjetoDto projetoDto) {

		Optional<ProjetoDto> projetoOptional = Optional.ofNullable(projetoDto);
		
		if (projetoOptional.isEmpty()) {
			return new MensagemDto(CAMPO_NULO_OU__EM_BRANCO);
		}
		
		if (!secretariaRepository.existsById(projetoDto.getIdSecretaria())) {
			return new MensagemDto(SECRETARIA_NAO_EXISTE);
		}
		Optional<SecretariaEntity> secretariaOptional = secretariaRepository.findById(projetoDto.getIdSecretaria());
		SecretariaEntity secretaria = secretariaOptional.get();
		if (projetoDto.getCusto() > secretaria.getOrcamentoProjetos()) {
			return new MensagemDto(ORCAMENTO_DE_PROJETO_NAO_EH_SUFICIENTE);
		}
		Double orcamentoDeProjeto = secretaria.getOrcamentoProjetos();
		Double custoProjeto = projetoDto.getCusto();
		secretaria.setOrcamentoProjetos(orcamentoDeProjeto - custoProjeto);
		projetoDto.setDataInicio(LocalDate.now());
		projetoDto.setConcluido(false);
		projetoDto.setDataEntrega(null);
		secretariaRepository.save(secretaria);
		ProjetoEntity projeto = projetoDto.transformaParaObjeto();
		projetoRepository.save(projeto);

		return new MensagemDto(PROJETO_CADASTRADO_COM_SUCESSO);
	}

	public List<ProjetoEntity> listaProjetos() {
		return (List<ProjetoEntity>) projetoRepository.findAll();
	}

	public ProjetoEntity buscaProjeto(Long idProjeto) {
		if (idProjeto.equals(null) || !projetoRepository.existsById(idProjeto)) {
			return null;
		}
		return projetoRepository.findById(idProjeto).get();
	}

	public MensagemDto alteraProjeto(Long idProjeto, ProjetoDto projetoDto) {
		Optional<Long> iDOptional = Optional.ofNullable(idProjeto);
		if (iDOptional.isEmpty() || iDOptional.get().equals(null)) {
			return new MensagemDto(CAMPO_NULO_OU__EM_BRANCO);
		}
		if (!projetoRepository.existsById(idProjeto)) {
			return new MensagemDto(PROJETO_NAO_ENCONTRADO);
		}

		if (!secretariaRepository.existsById(projetoDto.getIdSecretaria())) {
			return new MensagemDto(SECRETARIA_NAO_EXISTE);
		}
		
		Optional<ProjetoEntity> projetoOptional = projetoRepository.findById(idProjeto);
		ProjetoEntity projeto = projetoOptional.get();
		Long idSecretaria = projeto.getIdProjeto();

		Optional<SecretariaEntity> secretariaOptional = secretariaRepository.findById(idSecretaria);
		SecretariaEntity secretaria = secretariaOptional.get();
		Optional<SecretariaEntity> secretariaDestinoOptional = secretariaRepository.findById(projetoDto.getIdSecretaria());
		Double orcamentoDeProjeto = secretaria.getOrcamentoProjetos();
		SecretariaEntity secretariaDestino = secretariaDestinoOptional.get();
		Double orcamentoProjetoSecretariaDestino = secretariaDestino.getOrcamentoFolha();
		Double custoAtual = projeto.getCusto();
		Double novoCusto = projetoDto.getCusto();
		Double diferencaDeCusto = custoAtual - novoCusto;
		
		if (secretariaDestino.getIdSecretaria().equals(secretaria.getIdSecretaria())) {
			if (custoAtual < novoCusto && diferencaDeCusto > secretaria.getOrcamentoProjetos()) {
				return new MensagemDto(SECRETARIA_NAO_PODE_SER_ALTERADA_POR_ORCAMENTO);
			}
			
			secretaria.setOrcamentoProjetos(orcamentoDeProjeto + diferencaDeCusto);
			secretariaRepository.save(secretaria);
			projeto = projetoDto.transformaParaObjeto();
			projetoRepository.save(projeto);
			return new MensagemDto(FUNCIONARIO_ALTERADO_COM_SUCESSO);
		}

		if (orcamentoProjetoSecretariaDestino < novoCusto) {
			return new MensagemDto (ORCAMENTO_DE_PROJETO_NAO_EH_SUFICIENTE);
		}
		secretaria.setOrcamentoProjetos(orcamentoDeProjeto + custoAtual);
		secretariaDestino.setOrcamentoFolha(orcamentoProjetoSecretariaDestino - novoCusto);
		
		secretariaRepository.save(secretaria);
		secretariaRepository.save(secretariaDestino);
		projeto = projetoDto.transformaParaObjeto();
		projetoRepository.save(projeto);
		
		return new MensagemDto(FUNCIONARIO_ALTERADO_COM_SUCESSO);
	}

}
