package br.com.cooperativa.votacao.exception.enumerator;

import br.com.cooperativa.votacao.exception.ExceptionTemplate;
import org.springframework.http.HttpStatus;

public enum PautaExceptionEnum {

    ERRO_REGISTRAR_PAUTA    (new ExceptionTemplate(-1, "Erro: não foi possível registrar esta pauta.", HttpStatus.INTERNAL_SERVER_ERROR)),
    ERRO_BUSCAR_PAUTA       (new ExceptionTemplate(-1, "Erro: não foi possível buscar esta pauta.", HttpStatus.INTERNAL_SERVER_ERROR)),
    PAUTA_NAO_ENCONTRADA    (new ExceptionTemplate(-1, "Pauta não encontrada.", HttpStatus.NO_CONTENT));

    private final ExceptionTemplate exception;

    PautaExceptionEnum(ExceptionTemplate exception) {
        this.exception = exception;
    }

    public ExceptionTemplate getException() {
        return exception;
    }
}
