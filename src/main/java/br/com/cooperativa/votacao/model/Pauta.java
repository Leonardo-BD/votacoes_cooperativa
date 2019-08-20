package br.com.cooperativa.votacao.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.OffsetDateTime;

@ApiModel
public class Pauta {

    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty
    private String titulo;

    @ApiModelProperty
    private String descricao;

    @ApiModelProperty(hidden = true)
    private OffsetDateTime dataInsercao;

    public Pauta() {
    }

    public Pauta(Integer id, String titulo, String descricao, OffsetDateTime dataInsercao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInsercao = dataInsercao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public OffsetDateTime getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(OffsetDateTime dataInsercao) {
        this.dataInsercao = dataInsercao;
    }
}