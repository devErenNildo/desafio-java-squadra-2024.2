package org.enderecosquadra.domain.uf;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_UF")
public class UF {
    @Id
    @Column(name = "CODIGO_UF")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uf_seq")
    @SequenceGenerator(name = "uf_seq", sequenceName = "SEQUENCE_UF", allocationSize = 1)
    private Long codigoUF;
    private String sigla;
    private String nome;
    private Integer status;


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
