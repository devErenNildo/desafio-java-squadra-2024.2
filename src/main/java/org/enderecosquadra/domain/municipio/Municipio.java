package org.enderecosquadra.domain.municipio;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_MUNICIPIO")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "municipio_seq")
    @SequenceGenerator(name = "municipio_seq", sequenceName = "SEQUENCE_MUNICIPIO", allocationSize = 1)
    @Column(name = "CODIGO_MUNICIPIO")
    private Long codigoMunicipio;
    private String nome;
    private Integer status;

    @Column(name = "CODIGO_UF")
    private Long codigoUf;

}
