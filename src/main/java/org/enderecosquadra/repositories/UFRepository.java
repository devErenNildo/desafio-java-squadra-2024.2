package org.enderecosquadra.repositories;

import org.enderecosquadra.domain.uf.UF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UFRepository extends JpaRepository<UF, Long> {

    @Query("SELECT e FROM UF e WHERE " +
            "(:codigoUF IS NULL OR e.codigoUF = :codigoUF) AND " +
            "(:sigla IS NULL OR e.sigla = :sigla) AND " +
            "(:nome IS NULL OR e.nome LIKE %:nome%) AND " +
            "(:status IS NULL OR e.status = :status)")
    List<UF> findEstadoByParams(@Param("codigoUF") Long codigoUF,
                                    @Param("sigla") String sigla,
                                    @Param("nome") String nome,
                                    @Param("status") Integer status);

    boolean existsBySigla(String sigla);

    boolean existsByNome(String nome);
}
