package br.com.cooperativa.votacao.model;

import java.time.OffsetDateTime;

public class ResultadoVotacao {

    private Integer id;
    private Integer idPauta;
    private Integer votosFavoraveis;
    private Integer votosContrarios;
    private Boolean aprovada;
    private OffsetDateTime dataPublicacao;

    public ResultadoVotacao() {
    }

    public ResultadoVotacao(Integer idPauta, Integer votosFavoraveis, Integer votosContrarios, Boolean aprovada) {
        this.idPauta = idPauta;
        this.votosFavoraveis = votosFavoraveis;
        this.votosContrarios = votosContrarios;
        this.aprovada = aprovada;
    }

    public ResultadoVotacao(Integer id, Integer idPauta, Integer votosFavoraveis, Integer votosContrarios, Boolean aprovada, OffsetDateTime dataPublicacao) {
        this.id = id;
        this.idPauta = idPauta;
        this.votosFavoraveis = votosFavoraveis;
        this.votosContrarios = votosContrarios;
        this.aprovada = aprovada;
        this.dataPublicacao = dataPublicacao;
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

    public Integer getVotosFavoraveis() {
        return votosFavoraveis;
    }

    public void setVotosFavoraveis(Integer votosFavoraveis) {
        this.votosFavoraveis = votosFavoraveis;
    }

    public Integer getVotosContrarios() {
        return votosContrarios;
    }

    public void setVotosContrarios(Integer votosContrarios) {
        this.votosContrarios = votosContrarios;
    }

    public Boolean getAprovada() {
        return aprovada;
    }

    public void setAprovada(Boolean aprovada) {
        this.aprovada = aprovada;
    }

    public OffsetDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(OffsetDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
}
