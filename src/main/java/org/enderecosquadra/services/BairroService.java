package org.enderecosquadra.services;

import org.enderecosquadra.domain.bairro.Bairro;
import org.enderecosquadra.repositories.BairroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BairroService {

    @Autowired
    private BairroRepository bairroRepository;

    public List<Bairro> buscarBairroComParametros(Long codigoBairro, Long codigoMunicipio, String nome, Integer status){
        return bairroRepository.buscarBairroPorParametros(codigoBairro, codigoMunicipio, nome, status);
    }
}
