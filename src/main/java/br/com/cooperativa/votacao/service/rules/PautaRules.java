package br.com.cooperativa.votacao.service.rules;

import br.com.cooperativa.votacao.model.Pauta;

public class PautaRules {

    public static boolean versaoApiValida(short versaoApi) {
        return  versaoApi == 1; //Adicionar mais condicionais conforme houverem novas versões.
    }

    public static boolean modeloPautaValido(Pauta pauta) {
        return  pauta != null &&
                pauta.getTitulo() != null &&
                pauta.getDescricao() != null &&
                pauta.getTitulo().length() > 0 &&
                pauta.getDescricao().length() > 0;
    }
}
