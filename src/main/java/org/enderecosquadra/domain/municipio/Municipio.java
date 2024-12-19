package org.enderecosquadra.domain.municipio;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.enderecosquadra.domain.bairro.Bairro;
import org.enderecosquadra.domain.uf.UF;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_MUNICIPIO")
@JsonPropertyOrder({"codigoMunicipio","codigoUF", "nome", "status", "estado"})
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "municipio_seq")
    @SequenceGenerator(name = "municipio_seq", sequenceName = "SEQUENCE_MUNICIPIO", allocationSize = 1)
    @Column(name = "CODIGO_MUNICIPIO")
    private Long codigoMunicipio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CODIGO_UF", nullable = false)
    private UF uf;

    private String nome;
    private Integer status;

    @OneToMany(mappedBy = "municipio", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bairro> bairros = new ArrayList<>();



    // CONSTRUCTORS -------------------------------------------------------
    public Municipio() {

    }

    public Municipio(UF estado, String nome, Integer status) {
        setEstado(estado);
        setNome(nome);
        setStatus(status);
    }

    // GETTERS AND SETTERS

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public UF getUF() {
        return uf;
    }

    public void setEstado(UF uf) {
        this.uf = uf;
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

    public Long getCodigoUF(){
        return uf.getCodigoUF();
    }
}
