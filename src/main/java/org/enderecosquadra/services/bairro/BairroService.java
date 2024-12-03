package org.enderecosquadra.services.bairro;

import org.enderecosquadra.domain.bairro.Bairro;
import org.enderecosquadra.domain.bairro.BairroRequestDTO;
import org.enderecosquadra.domain.bairro.BairroRequestPutDTO;
import org.enderecosquadra.domain.bairro.BairroResponseDTO;
import org.enderecosquadra.domain.municipio.Municipio;
import org.enderecosquadra.exceptions.exception.ExceptionDeRetorno;
import org.enderecosquadra.repositories.BairroRepository;
import org.enderecosquadra.repositories.MunicipioRepository;
import org.enderecosquadra.services.municipio.MunicipioValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BairroService {

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private BairroValidacao bairroValidacao;

    @Autowired
    private MunicipioValidacao municipioValidacao;

    // BUSCAR OS BAIRROS
    public List<BairroResponseDTO> buscarBairroComParametros(Long codigoBairro, Long codigoMunicipio, String nome, Integer status){
        List<Bairro> bairros = bairroRepository.buscarBairroPorParametros(codigoBairro, codigoMunicipio, nome, status);
        return converterBairroEmDTO(bairros);
    }

    // ADICIONAR BAIRRO
    public List<BairroResponseDTO> adicionarBairro(BairroRequestDTO bairroRequestDTO){

        Municipio municipio = municipioValidacao.verificarSeMunicipioExiste(bairroRequestDTO.getCodigoMunicipio());

        bairroValidacao.verificarSeBairroExisteNoMunicipio(bairroRequestDTO.getNome(), bairroRequestDTO.getCodigoMunicipio());

       Bairro bairro = new Bairro(
                municipio,
                bairroRequestDTO.getNome(),
                bairroRequestDTO.getStatus()
        );

        bairroRepository.save(bairro);

        return converterBairroEmDTO(bairroRepository.findAll());
    }

    // EDITAR BAIRRO ======================================================================================
    public List<BairroResponseDTO> editarBairro(BairroRequestPutDTO bairroRequestPutDTO){
        Bairro bairroExistente = bairroValidacao.verificarSeBairroExiste(bairroRequestPutDTO.getCodigoBairro());

        Municipio municipio = municipioValidacao.verificarSeMunicipioExiste(bairroRequestPutDTO.getCodigoMunicipio());

        bairroExistente.setMunicipio(municipio);
        bairroExistente.setNome(bairroRequestPutDTO.getNome());
        bairroExistente.setStatus(bairroRequestPutDTO.getStatus());

        bairroValidacao.verificarSeBairroExisteNoMunicipio(bairroExistente.getNome(), bairroExistente.getMunicipio().getCodigoMunicipio());

        bairroRepository.save(bairroExistente);

        return buscarBairroComParametros(null, null, null, null);
    }

    // converter bairro em bairroDTO para ser exibido ==============================================================================================
    private List<BairroResponseDTO> converterBairroEmDTO(List<Bairro> bairros){
        return bairros.stream()
                .map(bairro -> new BairroResponseDTO(
                        bairro.getCodigoBairro(),
                        bairro.getMunicipio().getCodigoMunicipio(),
                        bairro.getNome(),
                        bairro.getStatus()
                )).collect(Collectors.toList());
    }
}
