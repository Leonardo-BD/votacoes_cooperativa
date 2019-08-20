package br.com.cooperativa.votacao.model;

import java.time.OffsetDateTime;

public class Associado {

    private Integer id;
    private String cpf;
    private OffsetDateTime dataInsercao;

    public Associado() {
    }

    public Associado(Integer id, String cpf, OffsetDateTime dataInsercao) {
        this.id = id;
        this.cpf = cpf;
        this.dataInsercao = dataInsercao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public OffsetDateTime getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(OffsetDateTime dataInsercao) {
        this.dataInsercao = dataInsercao;
    }
}
