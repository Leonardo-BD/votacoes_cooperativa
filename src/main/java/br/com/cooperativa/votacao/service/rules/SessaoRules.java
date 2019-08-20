package br.com.cooperativa.votacao.service.rules;

import br.com.cooperativa.votacao.model.Sessao;

import java.time.OffsetDateTime;

public class SessaoRules {

    public static boolean versaoApiValida(short versaoApi) {
        return  versaoApi == 1; //Adicionar mais condicionais conforme houverem novas versões.
    }

    public static boolean modeloSessaoValido(Sessao sessao) {
        return  sessao != null &&
                sessao.getIdPauta() != null;
    }

    public static boolean tempoExpiracaoDefinido(Integer tempoExpiracao) {
        return  tempoExpiracao != null &&
                tempoExpiracao > 0;
    }

    public static boolean sessaoAberta(Sessao sessao) {
        OffsetDateTime dataAtual = OffsetDateTime.now();

        return  sessao != null &&
                sessao.getDataAbertura() != null &&
                sessao.getDataEncerramento() != null &&
                dataAtual.isAfter(sessao.getDataAbertura()) &&
                dataAtual.isBefore(sessao.getDataEncerramento());
    }
}
