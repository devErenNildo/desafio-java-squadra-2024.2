package org.enderecosquadra.domain.bairro;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.enderecosquadra.domain.municipio.Municipio;

@Entity
@Table(name = "TB_BAIRRO")
@JsonPropertyOrder({"codigoBairro", "nome", "status", "municipio"})
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bairro_seq")
    @SequenceGenerator(name = "bairro_seq", sequenceName = "SEQUENCE_BAIRRO", allocationSize = 1)
    @Column(name = "CODIGO_BAIRRO")
    private Long codigoBairro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CODIGO_MUNICIPIO", nullable = false)
    private Municipio municipio;

    private String nome;
    private Integer status;


    // CONSTRUCTORS --------------------------------------------------------------------------------
    public Bairro() {

    }

    public Bairro(Municipio municipio, String nome, Integer status) {
        setMunicipio(municipio);
        setNome(nome);
        setStatus(status);
    }

    // GETTERS AND SETTERS --------------------------------------------------------------------------

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Long codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio codigoMunicipio) {
        this.municipio = codigoMunicipio;
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
