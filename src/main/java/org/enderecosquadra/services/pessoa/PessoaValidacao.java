package org.enderecosquadra.services.pessoa;

import org.enderecosquadra.domain.endereco.Endereco;
import org.enderecosquadra.domain.pessoa.Pessoa;
import org.enderecosquadra.exceptions.exception.ExceptionDeRetorno;
import org.enderecosquadra.repositories.BairroRepository;
import org.enderecosquadra.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaValidacao {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BairroRepository bairroRepository;

    protected void verificarSeLoginExiste(String login){
        if (pessoaRepository.existsByLogin(login)){
            throw new ExceptionDeRetorno("O login: " + login + " já esta em uso, tente outro login");
        }
    }


    protected Pessoa verificarSePessoaExistePorId(Long id){
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ExceptionDeRetorno(
                        "A pessoa com id: "
                        + id
                        + " não existe no banco de dados"
                ));
    }
}
