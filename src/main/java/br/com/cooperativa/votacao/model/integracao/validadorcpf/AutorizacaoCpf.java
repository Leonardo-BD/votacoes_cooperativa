package br.com.cooperativa.votacao.model.integracao.validadorcpf;

import com.google.gson.annotations.SerializedName;

public class AutorizacaoCpf {

    @SerializedName("status")
    private String status;

    public AutorizacaoCpf(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
