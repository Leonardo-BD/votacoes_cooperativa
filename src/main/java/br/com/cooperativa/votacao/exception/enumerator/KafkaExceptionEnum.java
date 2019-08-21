package br.com.cooperativa.votacao.exception.enumerator;

import br.com.cooperativa.votacao.exception.ExceptionTemplate;
import org.springframework.http.HttpStatus;

public enum KafkaExceptionEnum {

    ERRO_ENVIAR_RESULTADOS  (new ExceptionTemplate(-1, "Erro: resultados não enviados ao Kafka.", HttpStatus.INTERNAL_SERVER_ERROR));

    private final ExceptionTemplate exception;

    KafkaExceptionEnum(ExceptionTemplate exception) {
        this.exception = exception;
    }

    public ExceptionTemplate getException() {
        return exception;
    }
}
