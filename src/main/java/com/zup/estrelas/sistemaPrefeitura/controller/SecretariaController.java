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
import com.zup.estrelas.sistemaPrefeitura.entity.SecretariaEntity;
import com.zup.estrelas.sistemaPrefeitura.service.SecretariaService;

@RestController
@RequestMapping("/sistemaPrefeitura/secretaria")
public class SecretariaController {

	@Autowired
	SecretariaService secretariaService;

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDto criaSecretaria(@RequestBody SecretariaEntity secretaria) {
		return this.secretariaService.insereSecretaria(secretaria);
	}

	@GetMapping(path = "/{idSecretaria}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Optional<SecretariaEntity> buscaSecretaria(@PathVariable Long idSecretaria) {
		return secretariaService.buscaSecretaria(idSecretaria);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<SecretariaEntity> listaSecretarias() {
		return (List<SecretariaEntity>) secretariaService.listaSecretarias();
	}
	
	@DeleteMapping(path = "/{idSecretaria}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public MensagemDto removeSecretaria (@PathVariable Long idSecretaria) {
		return this.secretariaService.removeSecretaria(idSecretaria);		
	}
	
	@PutMapping(path = "/{idSecretaria}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public MensagemDto alteraSecretaria (@PathVariable Long idSecretaria, @RequestBody SecretariaEntity secretaria) {
		return this.secretariaService.alteraSecretaria(idSecretaria, secretaria);		
	}
	


}
