package org.enderecosquadra.services.municipio;

import org.enderecosquadra.domain.municipio.Municipio;
import org.enderecosquadra.domain.municipio.MunicipioRequestDTO;
import org.enderecosquadra.domain.municipio.MunicipioRequestPutDTO;
import org.enderecosquadra.domain.municipio.MunicipioResponseDTO;
import org.enderecosquadra.domain.uf.UF;
import org.enderecosquadra.exceptions.exception.ExceptionDeRetorno;
import org.enderecosquadra.repositories.MunicipioRepository;
import org.enderecosquadra.repositories.UFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MunicipioService {
//
    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private UFRepository ufRepository;

    @Autowired
    private MunicipioValidacao municipioValidacao;

    // BUSCA TODOS OS MUNICÍPIOS OU APENAS UM DEPENDENDO DOS PARÂMETROS ==========================================================================
    public List<MunicipioResponseDTO> buscarMunicipioComParametros(Long codigoMunicipio, Long codigoUF, String nome, Integer status) {
        List<Municipio> municipios = municipioRepository.buscarMunicipioPorParametros(codigoMunicipio, codigoUF, nome, status);
        return converterMunicipioEmDTO(municipios);
    }

    // ADICIONAR MUNICÍPIO======================================================================================================================
    public List<MunicipioResponseDTO> adicionarMunicipio(MunicipioRequestDTO municipioRequestDTO){

        // VERIFICA SE O NOME DO MUNICIO JA EXISTE NO BANCO DE DADOS
        municipioValidacao.validarNomeMunicipioExistente(municipioRequestDTO.getNome());

        // VERIFICA SE O ID DO ESTADO EXISTE NO BANCO DE DADOS
        UF uf = ufRepository.findById(municipioRequestDTO.getCodigoUF())
                .orElseThrow(() -> new ExceptionDeRetorno("Uf com o código: " + municipioRequestDTO.getCodigoUF() + " não existe no banco de dados !"));

        //VERIFICANDO SE O NOME DO MUNICIPIO É O MESMO DO UF
        municipioValidacao.compararNomeMunicipioComUf(uf.getNome(), municipioRequestDTO.getNome());

        // CRIA UM MUNICIPIO USANDO O DTO
        Municipio municipio = new Municipio(
                uf,
                municipioRequestDTO.getNome(),
                municipioRequestDTO.getStatus()
        );

        // SALVA NO BANCO DE DADOS
        municipioRepository.save(municipio);

        // RETORNA A LISTA COM OS MUNICIPIOS
        return converterMunicipioEmDTO(municipioRepository.findAll());
    }

    // EDITAR UM MUNICIPIO===========================================================================================================================
    public List<MunicipioResponseDTO> editarMunicipio(MunicipioRequestPutDTO municipioRequestPutDTO){
        Municipio municipioExiste = municipioValidacao.verificarSeMunicipioExiste(municipioRequestPutDTO.getCodigoMunicipio());

        UF uf = ufRepository.findById(municipioRequestPutDTO.getCodigoUF())
                        .orElseThrow(()-> new ExceptionDeRetorno("UF com o código: " + municipioRequestPutDTO.getCodigoUF() + " não existe"));

        //VERIFICANDO SE O NOME DO MUNICIPIO É O MESMO DO UF
        municipioValidacao.compararNomeMunicipioComUf(uf.getNome(), municipioRequestPutDTO.getNome());

        municipioExiste.setEstado(uf);
        municipioExiste.setNome(municipioRequestPutDTO.getNome());
        municipioExiste.setStatus(municipioRequestPutDTO.getStatus());

        // VERIFICAR SE O NOME DO MUNICIPIO JA EXISTE NO BANCO DE DADOS
        municipioValidacao.validarNomeMunicipioExistente(municipioExiste.getNome());

        municipioRepository.save(municipioExiste);

        return buscarMunicipioComParametros(null, null, null, null);

    }

    // converter Municipio em municipioDTO para ser exibido ==============================================================================================
    private List<MunicipioResponseDTO> converterMunicipioEmDTO(List<Municipio> municipios){
        return municipios.stream()
                .map(municipio -> new MunicipioResponseDTO(
                        municipio.getCodigoMunicipio(),
                        municipio.getEstado().getCodigoUF(),
                        municipio.getNome(),
                        municipio.getStatus()
                )).collect(Collectors.toList());
    }
}
