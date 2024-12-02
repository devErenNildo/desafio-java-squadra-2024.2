package org.enderecosquadra.services.uf;

import org.enderecosquadra.domain.uf.UF;
import org.enderecosquadra.exceptions.exception.ExceptionDeRetorno;
import org.enderecosquadra.repositories.UFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UFValidacao {

    @Autowired
    private UFRepository ufRepository;


    // VALIDA SE A SIGLA OU NOME JA EXISTEM NO BANCO DE DADOS
    protected void validarSiglaNomeExistente(String sigla, String nome) {
        if(ufRepository.existsBySigla(sigla))
            throw new ExceptionDeRetorno("A sigla " + sigla + " já existe no banco de dados");

        if(ufRepository.existsByNome(nome))
            throw new ExceptionDeRetorno("A nome " + nome + " Já existe no banco de dados");
    }


    //VALIDA SE O ESTADO EXISTE ATRAVÉS DO CÓDIGO_UF
    protected UF verificarSeEstadoExiste(Long id){
        return ufRepository.findById(id)
                .orElseThrow(() -> new ExceptionDeRetorno("O estado com o código " + id + " não existe no banco de dados"));
    }


}
