package org.enderecosquadra.exceptions.exception;

public class ExceptionDeRetorno extends RuntimeException {

    private final String mensagem;

    public ExceptionDeRetorno(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
