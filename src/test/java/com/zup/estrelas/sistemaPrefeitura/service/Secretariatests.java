package com.zup.estrelas.sistemaPrefeitura.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.zup.estrelas.sistemaPrefeitura.controller.SecretariaController;
import com.zup.estrelas.sistemaPrefeitura.dto.MensagemDto;
import com.zup.estrelas.sistemaPrefeitura.entity.SecretariaEntity;
import com.zup.estrelas.sistemaPrefeitura.repository.SecretariaRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class Secretariatests {

	@Mock
	SecretariaRepository secretariaRepository;

	// FIXME: Cris, nós só testamos no nível da camada de serviço
	// por enquanto, por isso, o injectmocks deveria estar aqui.
	@Mock
	SecretariaService secretariaService;

	@InjectMocks
	SecretariaController secretariaController;

	private static final String SECRETARIA_CADASTRADA_COM_SUCESSO = "Secretaria cadastrada com sucesso";

	@Test
	public void deveCriarsecretariaComSucesso() {
		SecretariaEntity secretaria = new SecretariaEntity();
		MensagemDto mensagem = new MensagemDto(SECRETARIA_CADASTRADA_COM_SUCESSO);
		secretaria.setArea("Saude");
		secretaria.setOrcamentoProjetos(50000.01d);
		secretaria.setOrcamentoFolha(3000d);
		secretaria.setTelefone("2255779900");
		secretaria.setEndereco("rua ali, numero aqui");
		secretaria.setSite("www.darthVader.estreladamorte.jaera");
		secretaria.setEmail("Foi@voltou.com");

		Mockito.when(secretariaService.insereSecretaria(secretaria)).thenReturn(mensagem);

	}

	@Test
	public void naoDeveCriarSecretaria() {

	}

	@Test
	public void deveListarSecretarias() {

	}

	@Test
	public void naoDeveListarSecretarias() {

	}

	@Test
	public void deveRemoverSecretariacomSucesso() {

	}

	@Test
	public void naoDeveRemoverSecretaria() {

	}
}
