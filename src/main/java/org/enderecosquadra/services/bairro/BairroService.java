package org.enderecosquadra.services.bairro;

import org.enderecosquadra.domain.bairro.Bairro;
import org.enderecosquadra.domain.bairro.BairroRequestDTO;
import org.enderecosquadra.domain.bairro.BairroRequestPutDTO;
import org.enderecosquadra.domain.bairro.BairroResponseDTO;
import org.enderecosquadra.domain.municipio.Municipio;
import org.enderecosquadra.exceptions.exception.ExceptionDeRetorno;
import org.enderecosquadra.repositories.BairroRepository;
import org.enderecosquadra.repositories.MunicipioRepository;
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
    private MunicipioRepository municipioRepository;

    // BUSCAR OS BAIRROS
    public List<BairroResponseDTO> buscarBairroComParametros(Long codigoBairro, Long codigoMunicipio, String nome, Integer status){
        List<Bairro> bairros = bairroRepository.buscarBairroPorParametros(codigoBairro, codigoMunicipio, nome, status);
        return converterBairroEmDTO(bairros);
    }

    // ADICIONAR BAIRRO
    public List<BairroResponseDTO> adicionarBairro(BairroRequestDTO bairroRequestDTO){
        bairroValidacao.validarMunicipioExistenteNomeBairro(bairroRequestDTO);

        Municipio municipio = municipioRepository.findById(bairroRequestDTO.getCodigoMunicipio())
                .orElseThrow(() -> new ExceptionDeRetorno("O município com o código: " + bairroRequestDTO.getCodigoMunicipio() + " não existe no banco de dados."));

       Bairro bairro = new Bairro(
                municipio,
                bairroRequestDTO.getNome(),
                bairroRequestDTO.getStatus()
        );
        bairroRepository.save(bairro);

        return converterBairroEmDTO(bairroRepository.findAll());
    }

    public List<BairroResponseDTO> editarBairro(BairroRequestPutDTO bairroRequestPutDTO){
        Bairro bairroExistente = bairroValidacao.verificarSeBairroExiste(bairroRequestPutDTO.getCodigoBairro());

        Municipio municipio = municipioRepository.findById(bairroRequestPutDTO.getCodigoMunicipio())
                .orElseThrow(() -> new ExceptionDeRetorno(
                        "Municipio com o código: "
                                + bairroRequestPutDTO.getCodigoMunicipio()
                                + "não exite")
                );

        bairroExistente.setMunicipio(municipio);
        bairroExistente.setNome(bairroRequestPutDTO.getNome());
        bairroExistente.setStatus(bairroRequestPutDTO.getStatus());

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
