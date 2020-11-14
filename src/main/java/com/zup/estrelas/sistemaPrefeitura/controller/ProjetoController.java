package com.zup.estrelas.sistemaPrefeitura.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.estrelas.sistemaPrefeitura.dto.MensagemDto;
import com.zup.estrelas.sistemaPrefeitura.dto.ProjetoDto;
import com.zup.estrelas.sistemaPrefeitura.entity.ProjetoEntity;
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
		public ProjetoEntity buscaProjeto(@PathVariable Long idProjeto) {
			return projetoService.buscaProjeto(idProjeto);
		}

		@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
		public List<ProjetoEntity> listaProjetos() {
			return (List<ProjetoEntity>) projetoService.listaProjetos();
		}

		@PutMapping(path = "/{idProjeto}", produces = { MediaType.APPLICATION_JSON_VALUE })
		public MensagemDto alteraProjeto(@PathVariable Long idProjeto, @RequestBody ProjetoDto projetoDto) {
			return this.projetoService.alteraProjeto(idProjeto, projetoDto);
		}

	}

