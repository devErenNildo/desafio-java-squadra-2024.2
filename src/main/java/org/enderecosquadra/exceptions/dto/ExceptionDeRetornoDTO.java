package org.enderecosquadra.exceptions.dto;

public class ExceptionDeRetornoDTO {
    private String mensagem;
    private Integer status;

//  CONSTRUCTOR ---------------------------------------------------------------
    public ExceptionDeRetornoDTO(String mensagem, Integer status) {
        this.mensagem = mensagem;
        this.status = status;
    }

//  GETTERS AND SETTERS ----------------------------------------------------------
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
