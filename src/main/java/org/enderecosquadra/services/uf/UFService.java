package org.enderecosquadra.services.uf;

import org.enderecosquadra.domain.uf.UF;
import org.enderecosquadra.domain.uf.UFRequestDTO;
import org.enderecosquadra.domain.uf.UFRequestPutDTO;
import org.enderecosquadra.exceptions.exception.ExceptionDeRetorno;
import org.enderecosquadra.repositories.UFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UFService {

    @Autowired
    private UFRepository ufRepository;

    @Autowired
    private UFValidacao ufValidacao;


    // BUSCAR ESTADO USANDO PARÂMETROS OU BUSCA SEM PARÂMETROS
    //DEVOLVE UMA LISTA COM TODOS OS ESTADOS OU APENAS UM ESTADO DEPENDENDO DO PARÂMETRO
    public List<UF> buscarUfComParametros(Long codigoUF, String sigla, String nome, Integer status) {
        return ufRepository.findEstadoByParams(codigoUF, sigla, nome, status);

    }


    // ADICIONA UM ESTADO NO BANCO DE DADOS,
    public List<UF> adicionarUf(UFRequestDTO ufRequestDTO) {

        // VALIDA SE A SIGLA E O NOME JA EXISTEM NO BANCO DE DADOS
        ufValidacao.validarSiglaNomeExistente(
                ufRequestDTO.getSigla(),
                ufRequestDTO.getNome()
        );

        // CRIA UM UF A PARTIR DO DTO
        UF uf = new UF(
                ufRequestDTO.getSigla(),
                ufRequestDTO.getNome(),
                ufRequestDTO.getStatus()
        );

        // SALVA O UF NO BANCO DE DADOS
        ufRepository.save(uf);

        // RETORNA UMA LISTA COM TODOS OS ESTADOS SALVOS
        return ufRepository.findAll();
    }

    // EDITAR UM ESTADO
    public List<UF> editarUf(UFRequestPutDTO ufRequestDTO){

        // VERIFICA SE O ESTADO EXISTE ATRAVÉS DO ID, SE NÃO ENCONTRAR É RETORNADO UMA EXCEPTION
        UF estadoExistente = ufValidacao.verificarSeEstadoExiste(ufRequestDTO.getCodigoUF());

        // EDITA OS VALORES DO ESTADO
        estadoExistente.setSigla(ufRequestDTO.getSigla());
        estadoExistente.setNome(ufRequestDTO.getNome());
        estadoExistente.setStatus(ufRequestDTO.getStatus());

        // VERIFICA SE OS NOVOS VALORES DO ESTADO JÁ EXISTEM NO BANCO DE DADOS
        ufValidacao.validarSiglaNomeExistente(
                estadoExistente.getSigla(),
                estadoExistente.getNome()
        );

        // SALVA O NOVO ESTADO NO BANCO DE DADOS
        ufRepository.save(estadoExistente);

        //RETORNA UMA LISTA COM TODOS OS ESTADOS
        return ufRepository.findAll();
    }
}
