package com.zup.estrelas.sistemaPrefeitura.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.zup.estrelas.sistemaPrefeitura.enums.AREA_SECRETARIA;

public class AlteraSecretariaDto {
	
	private Long idSecretaria;
	
	@Enumerated(EnumType.STRING)
	private AREA_SECRETARIA area;

}
