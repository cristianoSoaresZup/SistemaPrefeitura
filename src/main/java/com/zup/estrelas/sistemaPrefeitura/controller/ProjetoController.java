package com.zup.estrelas.sistemaPrefeitura.controller;

import java.util.List;
import java.util.Optional;

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
import com.zup.estrelas.sistemaPrefeitura.dto.ProjetoDto;
import com.zup.estrelas.sistemaPrefeitura.entity.SecretariaEntity;
import com.zup.estrelas.sistemaPrefeitura.service.ProjetoService;


	@RestController
	@RequestMapping("/sistemaPrefeitura/projeto")
	public class ProjetoController {

		@Autowired
		ProjetoService projetoService;

		@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
		public MensagemDto criaProjeto(@RequestBody ProjetoDto projeto) {
			return this.projetoService.insereProjeto(projeto);
		}

		@GetMapping(path = "/{idProjeto}", produces = { MediaType.APPLICATION_JSON_VALUE })
		public Optional<SecretariaEntity> buscaProjeto(@PathVariable Long idProjeto) {
			return secretariaService.buscaProjeto(idProjeto);
		}

		@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
		public List<SecretariaEntity> listaProjetos() {
			return (List<SecretariaEntity>) projetoService.listaProjetos();
		}

		@DeleteMapping(path = "/{idProjeto}", produces = { MediaType.APPLICATION_JSON_VALUE })
		public MensagemDto removeSecretaria(@PathVariable Long idProjeto) {
			return this.secretariaService.removeProjeto(idProjeto);
		}

		@PutMapping(path = "/{idProjeto}", produces = { MediaType.APPLICATION_JSON_VALUE })
		public MensagemDto alteraSecretaria(@PathVariable Long idProjeto, @RequestBody SecretariaEntity secretaria) {
			return this.secretariaService.alteraSecretaria(idProjeto, secretaria);
		}

	}

