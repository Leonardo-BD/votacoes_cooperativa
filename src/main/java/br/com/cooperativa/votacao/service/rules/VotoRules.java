package br.com.cooperativa.votacao.service.rules;

import br.com.cooperativa.votacao.model.Voto;
import br.com.cooperativa.votacao.integracao.model.retorno.ValidacaoCpfRetorno;

public class VotoRules {

    public static boolean versaoApiValida(short versaoApi) {
        return  versaoApi == 1; //Adicionar mais condicionais conforme houverem novas versões.
    }

    public static boolean modeloVotoValido(Voto voto) {
        return  voto != null &&
                voto.getIdSessao() != null &&
                voto.getIdSessao() > 0 &&
                voto.getIdAssociado() != null &&
                voto.getIdAssociado() > 0 &&
                voto.getValor() != null;
    }

    public static boolean cpfAutorizado(ValidacaoCpfRetorno validacaoCpfRetorno) {
        return  validacaoCpfRetorno != null &&
                validacaoCpfRetorno.getStatus() != null &&
                validacaoCpfRetorno.getStatus().equals("ABLE_TO_VOTE");
    }
}
