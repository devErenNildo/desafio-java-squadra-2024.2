package org.enderecosquadra.services.bairro;

import org.enderecosquadra.domain.bairro.Bairro;
import org.enderecosquadra.domain.bairro.BairroRequestDTO;
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

    protected void validarMunicipioExistenteNomeBairro(BairroRequestDTO bairroRequestDTO){
//        municipioValidacao.verificarSeMunicipioExiste(bairroRequestDTO.getCodigoMunicipio());

        if(bairroRepository.existsByNome(bairroRequestDTO.getNome())){
            if(bairroRepository.existsByCodigoMunicipio(bairroRequestDTO.getCodigoMunicipio())){
                throw new ExceptionDeRetorno(
                        "O bairro com o nome: "
                                + bairroRequestDTO.getNome()
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
