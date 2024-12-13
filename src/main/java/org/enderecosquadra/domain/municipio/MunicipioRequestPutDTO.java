package org.enderecosquadra.domain.municipio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MunicipioRequestPutDTO {
    @NotNull(message = "o campo codigoMunicipio é obrigatório")
    private Long codigoMunicipio;

    @NotNull(message = "o campo codigoUF é obrigatório")
    private Long codigoUF;

    @NotBlank(message = "o campo nome é obrigatório")
    private String nome;

    @NotNull(message = "o campo status é obrigatório")
    private Integer status;

    public MunicipioRequestPutDTO(Long codigoMunicipio, Long codigoUF, String nome, Integer status) {
        this.codigoMunicipio = codigoMunicipio;
        this.codigoUF = codigoUF;
        setNome(nome);
        this.status = status;
    }


    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

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
