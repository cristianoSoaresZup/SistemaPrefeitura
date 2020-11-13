package com.zup.estrelas.sistemaPrefeitura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.estrelas.sistemaPrefeitura.dto.MensagemDto;
import com.zup.estrelas.sistemaPrefeitura.entity.SecretariaEntity;
import com.zup.estrelas.sistemaPrefeitura.repository.SecretariaRepository;

@Service
public class SecretariaService {

	private static final String SECRETARIA_JA_EXISTE_OU_CAMPO_AREA_NULO = "Cadastro não pode ser efetuado sem a área de atuação da secretaria, ou a secretaria já existe";
	private static final String SECRETARIA_CADASTRADA_COM_SUCESSO = "Secretaria cadastrada com sucesso";
	private static final String SECRETARIA_ALTERADA_COM_SUCESSO = "Secretaria alterada com sucesso";
	private static final String SECRETARIA_REMOVIDA_COM_SUCESSO = "Secretaria removida com sucesso";
	private static final String SECRETARIA_NAO_ENCONTRADA = "Secretaria não existe em nosso banco de dados";

	@Autowired
	SecretariaRepository repository;

	public MensagemDto insereSecretaria(SecretariaEntity secretaria) {
		Optional<String> secretariaAreaOptional = Optional.ofNullable(secretaria.getArea());

		if (secretariaAreaOptional.isEmpty() || repository.existsByArea(secretaria.getArea())) {
			return new MensagemDto(SECRETARIA_JA_EXISTE_OU_CAMPO_AREA_NULO);
		}
		this.repository.save(secretaria);
		return new MensagemDto(SECRETARIA_CADASTRADA_COM_SUCESSO);
	}

	public List<SecretariaEntity> listaSecretarias() {
		return (List<SecretariaEntity>) repository.findAll();
	}

	public Optional<SecretariaEntity> buscaSecretaria(Long idSecretaria) {
		Optional<Long> secretariaIdOptional = Optional.ofNullable(idSecretaria);
		if (secretariaIdOptional.isPresent() && repository.existsById(idSecretaria)) {
			Optional<SecretariaEntity> secretaria = Optional.ofNullable(repository.findById(idSecretaria).get());
			return secretaria;
		}

		return Optional.empty();
	}

	public MensagemDto removeSecretaria(Long idSecretaria) {
		Optional<Long> secretariaIdOptional = Optional.ofNullable(idSecretaria);
		if (secretariaIdOptional.isPresent() && repository.existsById(idSecretaria)) {
			repository.deleteById(idSecretaria);
			return new MensagemDto(SECRETARIA_REMOVIDA_COM_SUCESSO);
		}
		return new MensagemDto(SECRETARIA_NAO_ENCONTRADA);
	}

	public MensagemDto alteraSecretaria(Long idSecretaria, SecretariaEntity secretaria) {
		Optional<Long> secretariaIdOptional = Optional.ofNullable(idSecretaria);
		if (secretariaIdOptional.isPresent() && repository.existsById(idSecretaria)) {
			this.repository.save(secretaria);
			return new MensagemDto(SECRETARIA_ALTERADA_COM_SUCESSO);
		}

		return new MensagemDto(SECRETARIA_NAO_ENCONTRADA);
	}

}