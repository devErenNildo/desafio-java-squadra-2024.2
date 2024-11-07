package org.enderecosquadra.repositories;

import org.enderecosquadra.domain.municipio.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    @Query(
            "SELECT e FROM Municipio e WHERE " +
            "(:codigoMunicipio IS NULL OR e.codigoMunicipio = :codigoMunicipio) AND " +
            "(:codigoUF IS NULL OR e.codigoUF = :codigoUF) AND " +
            "(:nome IS NULL OR e.nome LIKE %:nome%) AND " +
            "(:status IS NULL OR e.status = :status)"
    )
    List<Municipio> buscarMunicipioPorParametros(
            @Param("codigoMunicipio") Long codigoMunicipio,
            @Param("codigoUF") Long codigoUF,
            @Param("nome") String nome,
            @Param("status") Integer status
    );

    boolean existsByNome(String nome);
}
