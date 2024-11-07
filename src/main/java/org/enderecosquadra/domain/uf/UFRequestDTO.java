package org.enderecosquadra.domain.uf;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UFRequestDTO {

    @NotBlank(message = "o campo 'sigla' é obrigatório")
    private String sigla;

    @NotBlank(message = "o campo 'nome' é obrigatório")
    private String nome;

    @NotNull(message = "O campo 'status' é obrigatório")
    private Integer status;


//  CONSTRUCTORS ---------------------------------------------------------
    public UFRequestDTO() {

    }

    public UFRequestDTO(String sigla, String nome, Integer status) {
        setSigla(sigla);
        setNome(nome);
        setStatus(status);
    }

//  GETTERS AND SETTERS -----------------------------------------------------


    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla.toUpperCase();
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
