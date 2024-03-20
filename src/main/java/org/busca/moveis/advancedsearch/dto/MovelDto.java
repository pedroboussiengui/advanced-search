package org.busca.moveis.advancedsearch.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.busca.moveis.advancedsearch.entities.Endereco;
import org.busca.moveis.advancedsearch.entities.TipoImovel;
import org.busca.moveis.advancedsearch.entities.TipoNegocio;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class MovelDto {
    @NotNull(message = "O Endereço completo deve ser informado")
    private Endereco endereco;

    @NotEmpty(message = "O titulo deve ser informado.")
    private String titulo;

    @Min(value = 0, message = "O valor minimo para a area e 0.")
    private Integer area;

    @NotNull(message = "O tipo de imovel precisa ser informado.")
    private TipoImovel tipoImovel;

    @DecimalMin(value = "0.0", inclusive = false, message = "O valor do preço e invalido")
    @Digits(integer=3, fraction=2)
    @NotNull
    private BigDecimal preco;

    @DecimalMin(value = "0.0", inclusive = false, message = "O valor do condominio e invalido")
    @Digits(integer=3, fraction=2)
    private BigDecimal condominio;

    @DecimalMin(value = "0.0", inclusive = false, message = "O valor do IPTU e invalido.")
    @Digits(integer=3, fraction=2)
    private BigDecimal iptu;

    @Min(value = 0, message = "O valor minimo para quantidade de quartos e 0")
    private Integer qtdeQuartos;

    @Min(value = 0, message = "O valor minimo para quantidade de banheiros e 0")
    private Integer qtdeBanheiros;

    @Min(value = 0, message = "O valor minimo para quantidade de vagas e 0")
    private Integer qtdeVagas;

    @Size(max= 500, message = "A descriçao nao pode ultrapassar o limite de 500 caracteres.")
    private String descricao;

    @NotNull(message = "O tipo de negocio deve ser informado.")
    private TipoNegocio tipoNegocio;
}
