package br.com.cooperativa.votacao.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.OffsetDateTime;

@ApiModel
public class Sessao {

    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty
    private Integer idPauta;

    @ApiModelProperty(hidden = true)
    private Boolean contabilizada;

    @ApiModelProperty(hidden = true)
    private OffsetDateTime dataAbertura;

    @ApiModelProperty(hidden = true)
    private OffsetDateTime dataEncerramento;

    public Sessao() {
    }

    public Sessao(Integer id, Integer idPauta, Boolean contabilizada, OffsetDateTime dataAbertura, OffsetDateTime dataEncerramento) {
        this.id = id;
        this.idPauta = idPauta;
        this.contabilizada = contabilizada;
        this.dataAbertura = dataAbertura;
        this.dataEncerramento = dataEncerramento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Integer idPauta) {
        this.idPauta = idPauta;
    }

    public Boolean getContabilizada() {
        return contabilizada;
    }

    public void setContabilizada(Boolean contabilizada) {
        this.contabilizada = contabilizada;
    }

    public OffsetDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(OffsetDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public OffsetDateTime getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(OffsetDateTime dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    @Override
    public String toString() {
        return "Sessao{" +
                "id=" + id +
                ", idPauta=" + idPauta +
                ", contabilizada=" + contabilizada +
                ", dataAbertura=" + dataAbertura +
                ", dataEncerramento=" + dataEncerramento +
                '}';
    }
}