package com.zup.estrelas.sistemaPrefeitura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.estrelas.sistemaPrefeitura.dto.FuncionarioDto;
import com.zup.estrelas.sistemaPrefeitura.dto.MensagemDto;
import com.zup.estrelas.sistemaPrefeitura.entity.FuncionarioEntity;
import com.zup.estrelas.sistemaPrefeitura.entity.SecretariaEntity;
import com.zup.estrelas.sistemaPrefeitura.repository.FuncionarioRepository;
import com.zup.estrelas.sistemaPrefeitura.repository.SecretariaRepository;

@Service
public class ProjetoService {

	public static final String FUNCIONARIO_CADASTRADO_COM_SUCESSO = "Funcionario cadastrado com sucesso";
	public static final String FUNCIONARIO_ALTERADO_COM_SUCESSO = "Funcionario alterado com sucesso";
	public static final String ID_FUNCIONARIO_EXISTE = "O  id deste funcionario já existe";
	public static final String FUNCIONARIO_NAO_EXISTE = "O  id de funcionario informado não existe";
	public static final String ORCAMENTO_DE_FOLHA_NAO_EH_SUFICIENTE = "O salário do funcionario é maior do "
			+ "que o orçamento de folha disponível";
	public static final String CAMPO_NULO_OU_NAO_EXISTE = "Funcionario não pode ser cadastrado pelo campo "
			+ "secretaria estar nulo ou a secretaria informada não existe";

	@Autowired
	FuncionarioRepository funcionarioRepository;
	SecretariaRepository secretariaRepository;

	public MensagemDto insereFuncionario(FuncionarioDto funcionarioDto) {

		Optional<FuncionarioDto> funcionarioOptional = Optional.ofNullable(funcionarioDto);
		if (funcionarioOptional.isEmpty() || !secretariaRepository.existsById(funcionarioDto.getIdSecretaria())
				|| funcionarioRepository.existsById(funcionarioDto.getIdFuncionario())) {
			return new MensagemDto(CAMPO_NULO_OU_NAO_EXISTE);
		}
		
		Optional<SecretariaEntity> secretariaOptional = secretariaRepository.findById(funcionarioDto.getIdSecretaria());
		SecretariaEntity secretaria = secretariaOptional.get();
		if (funcionarioDto.getSalario() > secretaria.getOrcamentoFolha()) {
			return new MensagemDto(ORCAMENTO_DE_FOLHA_NAO_EH_SUFICIENTE);
		}
		Double orcamentoDeFolha = secretaria.getOrcamentoFolha();
		Double salario = funcionarioDto.getSalario();
		secretaria.setOrcamentoFolha(orcamentoDeFolha - salario);
		funcionarioDto.setSecretaria(secretaria);
		FuncionarioEntity funcionario = funcionarioDto.transformaParaObjeto();
		funcionarioRepository.save(funcionario);

		return new MensagemDto(FUNCIONARIO_CADASTRADO_COM_SUCESSO);
	}

	public List<FuncionarioEntity> listaFuncionarios() {
		return (List<FuncionarioEntity>) funcionarioRepository.findAll();
	}

	public FuncionarioEntity buscaFuncionario(Long idFuncionario) {
		if(idFuncionario.equals(null)) {
			return null;
		}
		return funcionarioRepository.findById(idFuncionario).get();
	}

	public boolean removeFuncionario(Long idFuncionario) {
		if (funcionarioRepository.existsById(idFuncionario)) {
			funcionarioRepository.deleteById(idFuncionario);
			return true;
		}
		return false;
	}

	public MensagemDto alteraFuncionario(FuncionarioDto funcionario) {
		Optional<Long> funcionarioOptional = Optional.ofNullable(funcionario.getIdFuncionario());
		if (funcionarioOptional.isEmpty() || !funcionarioRepository.existsById(funcionario.getIdFuncionario())) {
			return new MensagemDto(FUNCIONARIO_NAO_EXISTE);
		}

		return new MensagemDto(FUNCIONARIO_ALTERADO_COM_SUCESSO);
	}
