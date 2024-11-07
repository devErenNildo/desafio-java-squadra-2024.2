package org.enderecosquadra.domain.uf;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UFRequestPutDTO {

    @NotNull(message = "O campo 'id' é obrigatório")
    private Long codigoUF;

    @NotBlank(message = "O campo 'sigla' é obrigatório")
    private String sigla;

    @NotBlank(message = "O campo 'nome' é obrigatório")
    private String nome;

    @NotNull(message = "O campo 'status' é obrigatório")
    private Integer status;

    public UFRequestPutDTO() {

    }

    public UFRequestPutDTO(long codigoUF, String sigla, String nome, Integer status) {
        setCodigoUF(codigoUF);
        setSigla(sigla);
        setNome(nome);
        setStatus(status);
    }

    public long getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(long codigoUF) {
        this.codigoUF = codigoUF;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla.toUpperCase();
    }

    public String getNome() {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome.toUpperCase();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
