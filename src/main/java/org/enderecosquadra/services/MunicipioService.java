package org.enderecosquadra.services;

import org.enderecosquadra.domain.municipio.Municipio;
import org.enderecosquadra.repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    public List<Municipio> buscarMunicipioComParametros(Long codigoMunicipio, Long codigoUF, String nome, Integer status) {
        return municipioRepository.buscarMunicipioPorParametros(codigoMunicipio, codigoUF, nome, status);
    }
}
