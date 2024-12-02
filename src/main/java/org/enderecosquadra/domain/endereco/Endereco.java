package org.enderecosquadra.domain.endereco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.enderecosquadra.domain.bairro.Bairro;
import org.enderecosquadra.domain.pessoa.Pessoa;

import java.util.Optional;

@Entity
@Table(name = "TB_ENDERECO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonPropertyOrder({"codigoEndereco", "nomeRua", "numero", "complemento", "cep", "bairro"})
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEndereco")
    @SequenceGenerator(name = "seqEndereco", sequenceName = "SEQUENCE_ENDERECO", allocationSize = 1)
    @Column(name = "CODIGO_ENDERECO")
    private Long codigoEndereco;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CODIGO_PESSOA", nullable = false)
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CODIGO_BAIRRO", nullable = false)
    private Bairro bairro;

    @Column(name = "NOME_RUA")
    private String nomeRua;

    private String numero;

    private String complemento;

    private String cep;

    public Endereco(Pessoa pessoa, Bairro bairro, String nomeRua, String numero, String complemento, String cep) {
        this.pessoa = pessoa;
        this.bairro = bairro;
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Endereco() {

    }

    public Long getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(Long codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
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
