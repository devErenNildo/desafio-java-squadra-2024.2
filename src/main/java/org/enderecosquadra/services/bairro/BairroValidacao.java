package org.enderecosquadra.services.bairro;

import org.enderecosquadra.domain.bairro.Bairro;
import org.enderecosquadra.domain.bairro.BairroRequestDTO;
import org.enderecosquadra.domain.municipio.Municipio;
import org.enderecosquadra.exceptions.exception.ExceptionDeRetorno;
import org.enderecosquadra.repositories.BairroRepository;
import org.enderecosquadra.services.municipio.MunicipioValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BairroValidacao {

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private MunicipioValidacao municipioValidacao;


    protected void verificarSeBairroExisteNoMunicipio(String nomeBairro, Long codigoMunicipio){
        municipioValidacao.verificarSeMunicipioExiste(codigoMunicipio);

        if (bairroRepository.existsByNome(nomeBairro)){
            if (bairroRepository.existsByCodigoMunicipio(codigoMunicipio)){
                throw new ExceptionDeRetorno(
                        "O bairro com o nome: "
                                + nomeBairro
                                + " já esta cadastrado nesse município"
                );
            }
        }

    }

    // VERIFICAR SE O BAIRRO EXISTE BUSCANDO PELO ID
    protected Bairro verificarSeBairroExiste(Long id){
        return bairroRepository.findById(id)
                .orElseThrow(() -> new ExceptionDeRetorno(
                        "O bairro com id: "
                        + id + "não existe no banco de dados"
                ));
    }
}
