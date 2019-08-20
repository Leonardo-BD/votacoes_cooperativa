package br.com.cooperativa.votacao.exception.enumerator;

import br.com.cooperativa.votacao.exception.ExceptionTemplate;
import org.springframework.http.HttpStatus;

public enum GenericExceptionEnum {

    VERSAO_API_INVALIDA (new ExceptionTemplate(-1, "Erro: versão inexistente para esta API.", HttpStatus.NOT_FOUND)),
    MODELO_INVALIDO     (new ExceptionTemplate(-1, "Erro: verifique o correto preenchimento dos campos.", HttpStatus.BAD_REQUEST));

    private final ExceptionTemplate exception;

    GenericExceptionEnum(ExceptionTemplate exception) {
        this.exception = exception;
    }

    public ExceptionTemplate getException() {
        return exception;
    }
}
