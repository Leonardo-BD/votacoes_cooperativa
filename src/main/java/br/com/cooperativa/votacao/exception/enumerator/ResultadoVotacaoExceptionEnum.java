package br.com.cooperativa.votacao.exception.enumerator;

import br.com.cooperativa.votacao.exception.ExceptionTemplate;
import org.springframework.http.HttpStatus;

public enum ResultadoVotacaoExceptionEnum {

    ERRO_REGISTRAR_RESULTADO    (new ExceptionTemplate(-1, "Erro: não foi possível registrar resultado para esta pauta.", HttpStatus.INTERNAL_SERVER_ERROR)),
    ERRO_BUSCAR_RESULTADO       (new ExceptionTemplate(-1, "Erro: não foi possível buscar o resultado desta pauta.", HttpStatus.INTERNAL_SERVER_ERROR)),
    RESULTADO_NAO_ENCONTRADO    (new ExceptionTemplate(-1, "Resultado não encontrado para esta pauta.", HttpStatus.NO_CONTENT));

    private final ExceptionTemplate exception;

    ResultadoVotacaoExceptionEnum(ExceptionTemplate exception) {
        this.exception = exception;
    }

    public ExceptionTemplate getException() {
        return exception;
    }
}
