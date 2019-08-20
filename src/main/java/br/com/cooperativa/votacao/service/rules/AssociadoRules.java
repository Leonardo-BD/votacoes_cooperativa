package br.com.cooperativa.votacao.service.rules;

import br.com.cooperativa.votacao.model.Associado;

public class AssociadoRules {

    public static boolean modeloAssociadoValido(Associado associado) {
        return  associado != null &&
                associado.getCpf() != null &&
                associado.getCpf().length() == 11;
    }
}
