package org.enderecosquadra.domain.bairro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BairroRequestDTO {

    @NotNull(message = "O campo 'codigoId' é obrigatório")
    private Long codigoMunicipio;

    @NotBlank(message = "O campo 'nome' é obrigatório")
    private String nome;

    @NotNull(message = "O campo 'status' é obrigatório")
    private Integer status;


    // CONSTRUCTORS -----------------------------------------------------
    public BairroRequestDTO() {

    }

    public BairroRequestDTO(Long codigoMunicipio, String nome, Integer status) {
        setCodigoMunicipio(codigoMunicipio);
        setNome(nome);
        setStatus(status);
    }

    // GETTERS AND SETTERS -----------------------------------------------
    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
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
