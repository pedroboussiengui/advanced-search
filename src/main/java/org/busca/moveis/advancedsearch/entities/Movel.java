package org.busca.moveis.advancedsearch.entities;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Document("movel")
@Getter
@Builder
public class Movel {
    @Id
    private UUID id;
    @NotNull
    private Endereco endereco;
    @NotNull
    private String titulo;
    private Integer area;
    private TipoImovel tipoImovel;
    private Date createdAt;
    private BigDecimal preco;
    private BigDecimal condominio;
    private BigDecimal iptu;
    private Integer qtdeQuartos;
    private Integer qtdeBanheiros;
    private Integer qtdeVagas;
    private String descricao;
    private TipoNegocio tipoNegocio;
}
