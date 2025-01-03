package org.enderecosquadra.repositories;

import org.enderecosquadra.domain.bairro.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BairroRepository extends JpaRepository<Bairro, Long> {

    @Query(
            "SELECT e FROM Bairro e WHERE " +
                    "(:codigoBairro IS NULL OR e.codigoBairro = :codigoBairro) AND " +
                    "(:codigoMunicipio IS NULL OR e.municipio.codigoMunicipio = :codigoMunicipio) AND " +
                    "(:nome IS NULL OR e.nome LIKE %:nome%) AND " +
                    "(:status IS NULL OR e.status = :status)"
    )
    List<Bairro> buscarBairroPorParametros(
            @Param("codigoBairro") Long codigoBairro,
            @Param("codigoMunicipio") Long codigoMunicipio,
            @Param("nome") String nome,
            @Param("status") Integer status
    );

    boolean existsByNome(String nome);

    @Query("SELECT COUNT(b) > 0 FROM Bairro b WHERE b.municipio.codigoMunicipio = :codigoMunicipio")
    boolean existsByCodigoMunicipio(@Param("codigoMunicipio") Long codigoMunicipio);

    Bairro findByNome(String nome);


    @Query("SELECT COUNT(e) > 0 FROM Endereco e WHERE e.codigoPessoa.codigoPessoa = :codigoPessoa AND e.bairro.codigoBairro = :codigoBairro AND e.nomeRua = :nomeRua AND e.numero = :numero AND e.cep = :cep")
    boolean existsByCodigoPessoaAndEndereco(@Param("codigoPessoa") Long codigoPessoa, @Param("codigoBairro") Long codigoBairro, @Param("nomeRua") String nomeRua, @Param("numero") String numero, @Param("cep") String cep);
}
