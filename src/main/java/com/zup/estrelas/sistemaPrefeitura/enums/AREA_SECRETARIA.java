package com.zup.estrelas.sistemaPrefeitura.enums;

public enum AREA_SECRETARIA {

	SAUDE("secretaria de saúde"),
	TRANSITO("secretaria de transito"), 
	INFRAESTRUTURA("secretaria de infraestrutura e saneamento"),
	POLITICAS_SOCIAIS("secretaria de politicas sociais"), 
	EDUCACAO("secretaria de educação"),
	GERENCIAMNETO_DE_CRISE("gabinete de gerenciamento de crise");

	private String value;

	AREA_SECRETARIA(String string) {
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
