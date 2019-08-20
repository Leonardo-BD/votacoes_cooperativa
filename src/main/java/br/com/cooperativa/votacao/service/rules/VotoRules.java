package br.com.cooperativa.votacao.service.rules;

import br.com.cooperativa.votacao.model.Voto;
import br.com.cooperativa.votacao.model.integracao.validadorcpf.AutorizacaoCpf;

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

    public static boolean cpfAutorizado(AutorizacaoCpf autorizacaoCpf) {
        return  autorizacaoCpf != null &&
                autorizacaoCpf.getStatus() != null &&
                autorizacaoCpf.getStatus().equals("ABLE_TO_VOTE");
    }
}
