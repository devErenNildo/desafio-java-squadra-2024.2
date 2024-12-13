package org.enderecosquadra.domain.bairro;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BairroRequestPutDTO {

    @NotNull(message = "o campo codigoBairro é obrigatório")
    private Long codigoBairro;

    @NotNull(message = "o campo codigoMunicipio é obrigatório")
    private Long codigoMunicipio;

    @NotBlank(message = "o campo nome é obrigatório")
    private String nome;

    @NotNull(message = "o campo status é obrigatório")
    private Integer status;


    // CONSTRUTOR ========================================================================================
    public BairroRequestPutDTO(Long codigoBairro, Long codigoMunicipio, String nome, Integer status) {
        this.codigoBairro = codigoBairro;
        this.codigoMunicipio = codigoMunicipio;
        setNome(nome);
        this.status = status;
    }

    // GETTERS E SETTERS ==================================================================================

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Long codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

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
