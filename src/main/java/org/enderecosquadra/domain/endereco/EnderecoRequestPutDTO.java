package org.enderecosquadra.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EnderecoRequestPutDTO {
    private Long codigoEndereco;
    @NotNull(message = "O campo codigoBairro é obrigatório")
    private Long codigoBairro;
    @NotBlank(message = "O campo nomeRua é obrigatório")
    private String nomeRua;
    @NotBlank(message = "O campo numero é obrigatório")
    private String numero;
    @NotBlank(message = "O campo complemento é obrigatório")
    private String complemento;
    @NotBlank(message = "O campo cep é obrigatório")
    private String cep;

    public EnderecoRequestPutDTO(Long codigoEndereco, Long codigoBairro, String nomeRua, String numero, String complemento, String cep) {
        this.codigoEndereco = codigoEndereco;
        this.codigoBairro = codigoBairro;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

    public EnderecoRequestPutDTO(Long codigoBairro, String nomeRua, String numero, String complemento, String cep) {
        this.codigoBairro = codigoBairro;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

    public EnderecoRequestPutDTO(){

    }

    public Long getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(Long codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
    }

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Long codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
