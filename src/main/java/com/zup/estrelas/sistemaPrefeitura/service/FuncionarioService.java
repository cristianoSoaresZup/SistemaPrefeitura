package com.zup.estrelas.sistemaPrefeitura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.estrelas.sistemaPrefeitura.dto.MensagemDto;
import com.zup.estrelas.sistemaPrefeitura.entity.FuncionarioEntity;
import com.zup.estrelas.sistemaPrefeitura.repository.FuncionarioRepository;
import com.zup.estrelas.sistemaPrefeitura.repository.SecretariaRepository;

@Service
public class FuncionarioService {
	
	public static final String FUNCIONARIO_CADASTRADO_COM_SUCESSO = "Funcionario cadastrado com sucesso";
	public static final String FUNCIONARIO_NAO_CADASTRADO = "Funcionario não pode ser cadstrado pelo campo "
			+ "secretaria estar nulo ou a secretaria informada não existe";
	
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	SecretariaRepository seretariaRepository;
	
	public MensagemDto insereFuncionario(FuncionarioEntity funcionario) {
		
		
		Long idSecretaria = funcionario.getIdSecretaria();
		
		if (idSecretaria.equals(null) || seretariaRepository.existsById(idSecretaria)) {
			return new MensagemDto(FUNCIONARIO_NAO_CADASTRADO);
		}
		this.funcionarioRepository.save(funcionario);
		return new MensagemDto(FUNCIONARIO_CADASTRADO_COM_SUCESSO);
	}
	
	public List<FuncionarioEntity> listaFuncionarios() {
		return (List<FuncionarioEntity>) funcionarioRepository.findAll();
	}

	public FuncionarioEntity buscaFuncionario(Long idFuncionario) {
		return funcionarioRepository.findById(idFuncionario).get();
	}

	public boolean removeFuncionario(Long idFuncionario) {
		if (funcionarioRepository.existsById(idFuncionario)) {
			funcionarioRepository.deleteById(idFuncionario);
			return true;
		}
		return false;
	}

	public FuncionarioEntity alteraFuncionario(FuncionarioEntity idFuncionario) {
		if (funcionarioRepository.existsById(idFuncionario.getIdFuncionario())) {
			return funcionarioRepository.save(idFuncionario);
		}
		return null;
	}

}


