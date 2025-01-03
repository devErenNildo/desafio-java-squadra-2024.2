package org.enderecosquadra.services.municipio;

import org.enderecosquadra.domain.municipio.Municipio;
import org.enderecosquadra.exceptions.exception.ExceptionDeRetorno;
import org.enderecosquadra.repositories.MunicipioRepository;
import org.enderecosquadra.services.uf.UFValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MunicipioValidacao {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private UFValidacao ufValidacao;

    // VALIDAR SE A UF EXISTE E SE O MUNICÍPIO JA EXISTE NO BANCO DE DADOS
    protected void validarNomeMunicipioExistente(String nomeMunicipio) {
        if(municipioRepository.existsByNome(nomeMunicipio))
            throw new ExceptionDeRetorno("O município " + nomeMunicipio + " já existe no banco de dados");

    }

    // VERIFICA SE O MUNICÍPIO EXISTE BUSCANDO PELO ID
    public Municipio verificarSeMunicipioExiste(Long id){
        return municipioRepository.findById(id)
                .orElseThrow(() -> new ExceptionDeRetorno("O município com id: " +
                        id + " não existe no banco de dados"
                ));
    }

    public void compararNomeMunicipioComUf(String nomeUf, String nomeMunicipio){
        if(!nomeMunicipio.equals("SAO PAULO") && !nomeMunicipio.equals("RIO DE JANEIRO") && !nomeMunicipio.equals("GOIAS")){
            if(Objects.equals(nomeMunicipio, nomeUf))
                throw new ExceptionDeRetorno("O nome do município não pode ser o mesmo do UF");
        }
    }


}
