package org.busca.moveis.advancedsearch.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Endereco {
	private String cep;
    private String rua;
	private Integer numero;
	private String bairro;
	private String cidade;
	private String estado;
}
