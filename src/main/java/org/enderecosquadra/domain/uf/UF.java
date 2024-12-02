package org.enderecosquadra.domain.uf;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.enderecosquadra.domain.municipio.Municipio;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_UF")
@JsonPropertyOrder({"codigoUF", "sigla", "nome", "status"})
public class UF {
    @Id
    @Column(name = "CODIGO_UF")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uf_seq")
    @SequenceGenerator(name = "uf_seq", sequenceName = "SEQUENCE_UF", allocationSize = 1)
    private Long codigoUF;
    private String sigla;
    private String nome;
    private Integer status;

    @OneToMany(mappedBy = "uf", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Municipio> municipios = new ArrayList<>();


//  CONSTRUCTORS --------------------------------------------------------------------------
    public UF() {

    }

    public UF(String sigla, String nome, Integer status) {
        setSigla(sigla);
        setNome(nome);
        setStatus(status);
    }

//  GETTERS AND SETTERS ----------------------------------------------------------------

    public Long getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(Long codigoUF) {
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

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
