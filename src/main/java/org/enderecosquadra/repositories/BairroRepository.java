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
}
