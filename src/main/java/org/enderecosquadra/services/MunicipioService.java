package org.enderecosquadra.services;

import org.enderecosquadra.domain.municipio.Municipio;
import org.enderecosquadra.domain.municipio.MunicipioRequestDTO;
import org.enderecosquadra.repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private MunicipioValidacao municipioValidacao;

    // BUSCA TODOS OS MUNICÍPIOS OU APENAS UM DEPENDENDO DOS PARÂMETROS
    public List<Municipio> buscarMunicipioComParametros(Long codigoMunicipio, Long codigoUF, String nome, Integer status) {
        return municipioRepository.buscarMunicipioPorParametros(codigoMunicipio, codigoUF, nome, status);
    }

    // ADICIONAR MUNICÍPIO
    public List<Municipio> adicionarMunicipio(MunicipioRequestDTO municipioRequestDTO){
        municipioValidacao.validarUfExistenteNomeMunicipio(municipioRequestDTO);

        Municipio municipio = new Municipio(
                municipioRequestDTO.getCodigoUF(),
                municipioRequestDTO.getNome(),
                municipioRequestDTO.getStatus()
        );

        municipioRepository.save(municipio);

        return municipioRepository.findAll();
    }
}
