package org.enderecosquadra.services;

import org.enderecosquadra.domain.municipio.MunicipioRequestDTO;
import org.enderecosquadra.exceptions.exception.ExceptionDeRetorno;
import org.enderecosquadra.repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MunicipioValidacao {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private UFValidacao ufValidacao;

    // VALIDAR SE A UF EXISTE E SE O MUNICÍPIO JA EXISTE NO BANCO DE DADOS
    protected void validarUfExistenteNomeMunicipio(MunicipioRequestDTO municipioRequestDTO) {
        ufValidacao.verificarSeEstadoExiste(municipioRequestDTO.getCodigoUF());

        if(municipioRepository.existsByNome(municipioRequestDTO.getNome()))
            throw new ExceptionDeRetorno("O município " + municipioRequestDTO.getNome() + " já existe no banco de dados");
    }


}
