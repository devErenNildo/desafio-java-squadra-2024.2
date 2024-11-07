package org.enderecosquadra.domain.municipio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MunicipioRequestDTO {

    @NotNull(message = "O campo 'codigoUF' é obrigatório")
    private Long codigoUF;

    @NotBlank(message = "O campo 'nome' é obrigatório")
    private String nome;

    @NotNull(message = "O campo 'status' é obrigatório")
    private Integer status;

    // CONSTRUCTORS --------------------------------------------------
    public MunicipioRequestDTO() {

    }

    public MunicipioRequestDTO(Long codigoUF, String nome, Integer status) {
        this.codigoUF = codigoUF;
        this.nome = nome;
        this.status = status;
    }


    // GETTERS AND SETTERS ---------------------------------------------------

    public Long getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(Long codigoUF) {
        this.codigoUF = codigoUF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
