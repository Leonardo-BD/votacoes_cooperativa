package br.com.cooperativa.votacao.exception;

import org.springframework.http.HttpStatus;

public class ExceptionTemplate extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final int code;
    private final HttpStatus httpStatus;

    public ExceptionTemplate(){
        super();
        this.code = -1;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ExceptionTemplate(Exception e){
        super(e);
        this.code = -1;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ExceptionTemplate(int code, String message, HttpStatus httpStatus){
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
