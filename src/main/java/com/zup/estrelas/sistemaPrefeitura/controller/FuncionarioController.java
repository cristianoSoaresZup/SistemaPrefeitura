package com.zup.estrelas.sistemaPrefeitura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.estrelas.sistemaPrefeitura.dto.MensagemDto;
import com.zup.estrelas.sistemaPrefeitura.entity.FuncionarioEntity;
import com.zup.estrelas.sistemaPrefeitura.service.FuncionarioService;

@RestController
@RequestMapping("/sistemaPrefeitura/funcionario")
public class FuncionarioController {
	@Autowired
	FuncionarioService funcionarioService;

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDto insereFuncionario(@RequestBody FuncionarioEntity funcionario) {
		return this.funcionarioService.insereFuncionario(funcionario);
	}

	@GetMapping(path = "/{idSecretaria}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public FuncionarioEntity buscaFuncionario(@PathVariable Long idFuncionario) {
		return funcionarioService.buscaFuncionario(idFuncionario);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<FuncionarioEntity> listaSecretarias() {
		return (List<FuncionarioEntity>) funcionarioService.listaFuncionarios();
	}

	@DeleteMapping(path = "/{idSecretaria}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public boolean removeSecretaria(@PathVariable Long idFuncionario) {
		return this.funcionarioService.removeFuncionario(idFuncionario);
	}

	@PutMapping(path = "/{idSecretaria}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public FuncionarioEntity alteraSecretaria(@PathVariable Long idFuncionario,
			@RequestBody FuncionarioEntity funcionario) {
		return this.funcionarioService.alteraFuncionario(funcionario);
	}

//		@PutMapping(path = "/{idSecretaria}", produces = { MediaType.APPLICATION_JSON_VALUE })
//		public MensagemDTO alteraSecretaria(@PathVariable Long idSecretaria, @RequestBody AlteraSecretariaDTO secretaria) {
	//
//			return secretariaService.alteraSecretaria(idSecretaria, secretaria);
//		}

}
