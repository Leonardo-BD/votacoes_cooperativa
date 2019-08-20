package br.com.cooperativa.votacao.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.OffsetDateTime;

@ApiModel
public class Voto {

    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty
    private Integer idSessao;

    @ApiModelProperty
    private Integer idAssociado;

    @ApiModelProperty
    private Boolean valor;

    @ApiModelProperty(hidden = true)
    private OffsetDateTime dataInsercao;

    public Voto() {
    }

    public Voto(Integer id, Integer idSessao, Integer idAssociado, Boolean valor, OffsetDateTime dataInsercao) {
        this.id = id;
        this.idSessao = idSessao;
        this.idAssociado = idAssociado;
        this.valor = valor;
        this.dataInsercao = dataInsercao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Integer idSessao) {
        this.idSessao = idSessao;
    }

    public Integer getIdAssociado() {
        return idAssociado;
    }

    public void setIdAssociado(Integer idAssociado) {
        this.idAssociado = idAssociado;
    }

    public Boolean getValor() {
        return valor;
    }

    public void setValor(Boolean valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(OffsetDateTime dataInsercao) {
        this.dataInsercao = dataInsercao;
    }
}