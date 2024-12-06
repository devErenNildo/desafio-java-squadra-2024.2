package org.enderecosquadra.repositories;

import org.enderecosquadra.domain.pessoa.Pessoa;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query(
            "SELECT e FROM Pessoa e WHERE " +
            "(:codigoPessoa IS NULL OR e.codigoPessoa = :codigoPessoa) AND " +
            "(:login IS NULL OR e.login = :login) AND " +
            "(:status IS NULL OR e.status = :status) "
    )
    List<Pessoa> buscarPessoaPorParametros(
            @Param("codigoPessoa") Long codigoPessoa,
            @Param("login") String login,
            @Param("status") Integer status
    );

//@Query("SELECT p FROM Pessoa p " +
//        "LEFT JOIN FETCH p.enderecos e " +
//        "LEFT JOIN FETCH e.bairro b " +
//        "LEFT JOIN FETCH b.municipio m " +
//        "LEFT JOIN FETCH m.uf u " +
//        "WHERE p.codigoPessoa = :codigoPessoa")
@Query("SELECT p FROM Pessoa p " +
        "LEFT JOIN FETCH p.enderecos e " +
        "LEFT JOIN FETCH e.bairro b " +
        "LEFT JOIN FETCH b.municipio m " +
        "LEFT JOIN FETCH m.uf u " +
        "WHERE p.codigoPessoa = :codigoPessoa")
    Pessoa pessoaDetalhada(@Param("codigoPessoa") Long codigoPessoa);

    boolean existsByLogin(String login);
}


