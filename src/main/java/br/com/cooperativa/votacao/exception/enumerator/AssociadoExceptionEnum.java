package br.com.cooperativa.votacao.exception.enumerator;

import br.com.cooperativa.votacao.exception.ExceptionTemplate;
import org.springframework.http.HttpStatus;

public enum AssociadoExceptionEnum {

    ERRO_REGISTRAR_ASSOCIADO    (new ExceptionTemplate(-1, "Erro: não foi possível registrar o associado.", HttpStatus.INTERNAL_SERVER_ERROR)),
    ERRO_BUSCAR_ASSOCIADO       (new ExceptionTemplate(-1, "Erro: não foi possível buscar o associado.", HttpStatus.INTERNAL_SERVER_ERROR)),
    ASSOCIADO_NAO_ENCONTRADO    (new ExceptionTemplate(-1, "Associado não encontrado para este ID.", HttpStatus.NO_CONTENT));

    private final ExceptionTemplate exception;

    AssociadoExceptionEnum(ExceptionTemplate exception) {
        this.exception = exception;
    }

    public ExceptionTemplate getException() {
        return exception;
    }
}
