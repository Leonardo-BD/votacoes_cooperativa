package br.com.cooperativa.votacao.exception.enumerator;

import br.com.cooperativa.votacao.exception.ExceptionTemplate;
import org.springframework.http.HttpStatus;

public enum SessaoExceptionEnum {

    ERRO_REGISTRAR_SESSAO       (new ExceptionTemplate(-1, "Erro: não foi possível registrar sessão para esta pauta.", HttpStatus.INTERNAL_SERVER_ERROR)),
    ERRO_CONTABILIZAR_SESSAO    (new ExceptionTemplate(-1, "Erro: não foi possível registrar sessão como contabilizada.", HttpStatus.INTERNAL_SERVER_ERROR)),
    ERRO_BUSCAR_SESSAO          (new ExceptionTemplate(-1, "Erro: não foi possível localizar esta sessão.", HttpStatus.INTERNAL_SERVER_ERROR)),
    SESSAO_NAO_ENCONTRADA       (new ExceptionTemplate(-1, "Sessão não localizada.", HttpStatus.NO_CONTENT)),
    PAUTA_INEXISTENTE           (new ExceptionTemplate(-1, "A sessão não foi criada: pauta não existe.", HttpStatus.NO_CONTENT));

    private final ExceptionTemplate exception;

	private SessaoExceptionEnum(ExceptionTemplate exception){
        this.exception = exception;
    }

    public ExceptionTemplate getException(){
        return this.exception;
    }
}
