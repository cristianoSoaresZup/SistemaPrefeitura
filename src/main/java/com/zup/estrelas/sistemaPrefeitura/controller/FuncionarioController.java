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

import com.zup.estrelas.sistemaPrefeitura.dto.FuncionarioDto;
import com.zup.estrelas.sistemaPrefeitura.dto.MensagemDto;
import com.zup.estrelas.sistemaPrefeitura.entity.FuncionarioEntity;
import com.zup.estrelas.sistemaPrefeitura.service.FuncionarioService;

@RestController
@RequestMapping("/sistemaPrefeitura/funcionario")
// FIXME: Cris, é uma boa prática nomear
// os recursos no plural, o ideal aqui seria
// /sistemaPrefeitura/funcionarios
public class FuncionarioController {
	@Autowired
	FuncionarioService funcionarioService;

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDto insereFuncionario(@RequestBody FuncionarioDto funcionario) {
		return this.funcionarioService.insereFuncionario(funcionario);
	}

	@GetMapping(path = "/{idFuncionario}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public FuncionarioEntity buscaFuncionario(@PathVariable Long idFuncionario) {
		return funcionarioService.buscaFuncionario(idFuncionario);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<FuncionarioEntity> listaSecretarias() {
		return (List<FuncionarioEntity>) funcionarioService.listaFuncionarios();
	}

	@DeleteMapping(path = "/{idFuncionario}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDto removeSecretaria(@PathVariable Long idFuncionario) {
		return this.funcionarioService.removeFuncionario(idFuncionario);
	}

	@PutMapping(path = "/{idFuncionario}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDto alteraFuncionario(@PathVariable Long idFuncionario, @RequestBody FuncionarioDto funcionario) {

		return funcionarioService.alteraFuncionario(idFuncionario, funcionario);
	}

}
