package br.com.cooperativa.votacao.integracao.model.retorno;

import com.google.gson.annotations.SerializedName;

public class ValidacaoCpfRetorno {

    @SerializedName("status")
    private String status;

    public ValidacaoCpfRetorno(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
