package br.com.cooperativa.votacao.exception.enumerator;

import br.com.cooperativa.votacao.exception.ExceptionTemplate;
import org.springframework.http.HttpStatus;

public enum VotoExceptionEnum {

    ERRO_REGISTRAR_VOTO     (new ExceptionTemplate(-1, "Erro: não foi possível registrar este voto.", HttpStatus.INTERNAL_SERVER_ERROR)),
    ERRO_BUSCAR_VOTOS       (new ExceptionTemplate(-1, "Erro: não foi possível localizar votos para esta sessão.", HttpStatus.INTERNAL_SERVER_ERROR)),
    VOTOS_NAO_ENCONTRADOS   (new ExceptionTemplate(-1, "Não foram encontrados votos para esta sessão.", HttpStatus.NO_CONTENT)),
    ASSOCIADO_JA_VOTOU      (new ExceptionTemplate(-1, "Voto não computado: o associado já votou nesta pauta.", HttpStatus.NO_CONTENT)),
    SESSAO_FECHADA          (new ExceptionTemplate(-1, "Voto não computado: a sessão de votação está fechada.", HttpStatus.NO_CONTENT)),
    CPF_NAO_AUTORIZADO      (new ExceptionTemplate(-1, "Voto não computado: o associado não pode votar.", HttpStatus.NO_CONTENT));

    private final ExceptionTemplate exception;

    VotoExceptionEnum(ExceptionTemplate exception) {
        this.exception = exception;
    }

    public ExceptionTemplate getException() {
        return exception;
    }
}