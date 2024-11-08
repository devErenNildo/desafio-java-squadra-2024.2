package org.enderecosquadra.domain.bairro;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_BAIRRO")
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bairro_seq")
    @SequenceGenerator(name = "bairro_seq", sequenceName = "SEQUENCE_BAIRRO", allocationSize = 1)
    @Column(name = "CODIGO_BAIRRO")
    private Long codigoBairro;

    @Column(name = "CODIGO_MUNICIPIO")
    private Long codigoMunicipio;
    private String nome;
    private Integer status;


    // CONSTRUCTORS --------------------------------------------------------------------------------
    public Bairro() {

    }

    public Bairro(Long codigoBairro, Long codigoMunicipio, String nome, Integer status) {
        setCodigoBairro(codigoBairro);
        setCodigoMunicipio(codigoMunicipio);
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
