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
public class FuncionarioService {

	public static final String FUNCIONARIO_CADASTRADO_COM_SUCESSO = "Funcionario cadastrado com sucesso";
	public static final String FUNCIONARIO_ALTERADO_COM_SUCESSO = "Funcionario alterado com sucesso";
	public static final String FUNCIONARIO_REMOVIDO_COM_SUCESSO = "Funcionario alterado com sucesso";
	public static final String ID_FUNCIONARIO_EXISTE = "O  id deste funcionario já existe";
	public static final String FUNCIONARIO_NAO_EXISTE = "O  id de funcionario informado não existe";
	public static final String SECRETARIA_NAO_EXISTE = "O id da secretaria de destino não existe";
	public static final String ORCAMENTO_DE_FOLHA_NAO_EH_SUFICIENTE = "O salário do funcionario é maior do "
			+ "que o orçamento de folha disponível na secretaria";
	public static final String CAMPO_NULO_OU__EM_BRANCO = "Existem campos de dados informados que estão nulos ou em branco";
	public static final String SALARIO_NAO_PODE_SER_REDUZIDO = "O salario não pode ser reduzido";

	@Autowired
	FuncionarioRepository funcionarioRepository;
	//FIXME: Faltou um autowired aqui.
	SecretariaRepository secretariaRepository;

	public MensagemDto insereFuncionario(FuncionarioDto funcionarioDto) {

		Optional<FuncionarioDto> funcionarioOptional = Optional.ofNullable(funcionarioDto);
		if (funcionarioOptional.isEmpty() || funcionarioOptional.get().getNome().equals(null)) {
			return new MensagemDto(CAMPO_NULO_OU__EM_BRANCO);
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
		if (idFuncionario.equals(null) || !funcionarioRepository.existsById(idFuncionario)) {
			return null;
		}
		return funcionarioRepository.findById(idFuncionario).get();
	}

	public MensagemDto removeFuncionario(Long idFuncionario) {
		Optional<Long> iDOptional = Optional.ofNullable(idFuncionario);
		if (iDOptional.isEmpty() || iDOptional.get().equals(null)) {
			return new MensagemDto(CAMPO_NULO_OU__EM_BRANCO);
		}
		if (!funcionarioRepository.existsById(idFuncionario)) {
			return new MensagemDto(FUNCIONARIO_NAO_EXISTE);
		}
		Optional<FuncionarioEntity> funcionarioOptional = funcionarioRepository.findById(idFuncionario);
		FuncionarioEntity funcionario = funcionarioOptional.get();

		Optional<SecretariaEntity> secretariaOptional = secretariaRepository.findById(funcionario.getIdSecretraria());
		SecretariaEntity secretaria = secretariaOptional.get();

		Double orcamentoDeFolha = secretaria.getOrcamentoFolha();
		Double salario = funcionario.getSalario();
		secretaria.setOrcamentoFolha(orcamentoDeFolha + salario);

		secretariaRepository.save(secretaria);
		funcionarioRepository.delete(funcionario);

		return new MensagemDto(FUNCIONARIO_REMOVIDO_COM_SUCESSO);
	}

	public MensagemDto alteraFuncionario(Long idFuncionario, FuncionarioDto funcionarioDto) {
		Optional<Long> iDOptional = Optional.ofNullable(idFuncionario);
		if (iDOptional.isEmpty() || iDOptional.get().equals(null)) {
			return new MensagemDto(CAMPO_NULO_OU__EM_BRANCO);
		}
		if (!funcionarioRepository.existsById(idFuncionario)) {
			return new MensagemDto(FUNCIONARIO_NAO_EXISTE);
		}

		if (!secretariaRepository.existsById(funcionarioDto.getIdSecretaria())) {
			return new MensagemDto(SECRETARIA_NAO_EXISTE);
		}
		Optional<FuncionarioEntity> funcionarioOptional = funcionarioRepository.findById(idFuncionario);
		FuncionarioEntity funcionario = funcionarioOptional.get();
		Double salario = funcionario.getSalario();
		Double novoSalario = funcionarioDto.getSalario();

		//FIXME: Seria legal criar um método privado 
		// (tipo uma função) com essa alteração de salário
		// entre secretarias para deixar esse método aqui
		// mais enxuto.
		Optional<SecretariaEntity> secretariaOptional = secretariaRepository.findById(funcionario.getIdSecretraria());
		SecretariaEntity secretaria = secretariaOptional.get();
		Optional<SecretariaEntity> secretariaDestinoOptional = secretariaRepository.findById(funcionarioDto.getIdSecretaria());
		Double orcamentoDeFolha = secretaria.getOrcamentoFolha();
		SecretariaEntity secretariaDestino = secretariaDestinoOptional.get();
		Double orcamentoFolhaSecretariaDestino = secretariaDestino.getOrcamentoFolha();

		if (funcionarioDto.getIdSecretaria().equals(secretaria.getIdSecretaria())) {
			if (salario > novoSalario) {
				return new MensagemDto(SALARIO_NAO_PODE_SER_REDUZIDO);
			}

			funcionario = funcionarioDto.transformaParaObjeto();
			funcionarioRepository.save(funcionario);
			return new MensagemDto(FUNCIONARIO_ALTERADO_COM_SUCESSO);
		}

		if (orcamentoFolhaSecretariaDestino < novoSalario) {
			return new MensagemDto (ORCAMENTO_DE_FOLHA_NAO_EH_SUFICIENTE);
		}
		secretaria.setOrcamentoFolha(orcamentoDeFolha + salario);
		secretariaDestino.setOrcamentoFolha(orcamentoFolhaSecretariaDestino - novoSalario);
		funcionario = funcionarioDto.transformaParaObjeto();
		secretariaRepository.save(secretaria);
		secretariaRepository.save(secretariaDestino);
		funcionarioRepository.save(funcionario);

		return new MensagemDto(FUNCIONARIO_ALTERADO_COM_SUCESSO);
	}

}
